package cc.asjks.bms.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.asjks.bms.server.mapper.*;
import cc.asjks.bms.server.model.*;
import cc.asjks.bms.server.service.*;

@Service("moneyService")
public class MoneyServiceImpl implements MoneyService{
	@Autowired
	MoneyMapper moneyMappler;
	@Autowired
	DetailsMapper detailsMappler;
	
	@Override
	public Double queryBlanaceById(Map<String, Object> map) {
		int id=Integer.valueOf(String.valueOf(map.get("uid")));
		String currency=String.valueOf(map.get("currency"));
		switch (currency){
			case "CNY":{
				Money m1=moneyMappler.selectCNYById(id);
				if(!(m1==null)) {
					return Double.valueOf(m1.getCNY());
				}
				return 0.00;
			}
			case "USD":{
				Money m1=moneyMappler.selectUSDById(id);
				if(!(m1==null)) {
					return Double.valueOf(m1.getUSD());
				}
				return 0.00;
			}
		}
		return 0.00;
	}
	@Override
	public int transferAccountsById(Map<String, Object> map) {
		//{"username":"admin","token":"b46ea31502e78405b7dfc116d3a48c02","currency":"USD","to":"","amount":""}
		Map<String, Object> fromMap=new HashMap<>();
		Map<String, Object> toMap=new HashMap<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time=sdf.format(new Date());
		Details d1=new Details();
		try {
			d1.setUid(Integer.valueOf(String.valueOf(map.get("uid"))));
			d1.setCurrency(String.valueOf(map.get("currency")));
			d1.setToUid(Integer.valueOf(String.valueOf(map.get("to"))));
			d1.setAmount(Double.valueOf(String.valueOf(map.get("amount"))));
			d1.setDate(Time);
			d1.setType("0");
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		fromMap.put("uid", d1.getUid());
		fromMap.put("currency", d1.getCurrency());
		toMap.put("uid", d1.getToUid());
		toMap.put("currency", d1.getCurrency());
		
		double nowBalFrom=queryBlanaceById(fromMap);
		double nowBalTo=queryBlanaceById(toMap);
		double newBalFrom=nowBalFrom-d1.getAmount();
		double newBalTo=nowBalTo+d1.getAmount();
		if(!(newBalFrom<0)) {
			int i=transferAccounts(d1,newBalFrom,newBalTo);
			int i2=detailsMappler.writeDetails(d1);
			if(i==1&&i2==1) {
				return 1;
			}
		}
		return 0;
	}
	
	@Override
	public int depositById(Map<String, Object> map) {
		Map<String, Object> m=new HashMap<>();
		Details d1=new Details();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time=sdf.format(new Date());
		try {
			d1.setUid(Integer.valueOf(String.valueOf(map.get("uid"))));
			d1.setCurrency(String.valueOf(map.get("currency")));
			d1.setAmount(Double.valueOf(String.valueOf(map.get("amount"))));
			d1.setDate(Time);
			d1.setType("1");
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		m.put("uid", d1.getUid());
		m.put("currency", d1.getCurrency());
		double nowBal=queryBlanaceById(m);
		Money money=new Money();
		money.setUid(d1.getUid());
		money.setCNY(d1.getAmount()+nowBal);
		money.setUSD(d1.getAmount()+nowBal);
		int i=updateDeposit(d1.getCurrency(),money);
		int i2=detailsMappler.writeDepositDetails(d1);
		if(i==1&&i2==1) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int withdrawById(Map<String, Object> map) {
		Map<String, Object> m=new HashMap<>();
		Details d1=new Details();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time=sdf.format(new Date());
		try {
			d1.setUid(Integer.valueOf(String.valueOf(map.get("uid"))));
			d1.setCurrency(String.valueOf(map.get("currency")));
			d1.setAmount(Double.valueOf(String.valueOf(map.get("amount"))));
			d1.setDate(Time);
			d1.setType("2");
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		m.put("uid", d1.getUid());
		m.put("currency", d1.getCurrency());
		double nowBal=queryBlanaceById(m);
		Money money=new Money();
		money.setUid(d1.getUid());
		money.setCNY(nowBal-d1.getAmount());
		money.setUSD(nowBal-d1.getAmount());
		if(money.getCNY()<0&&money.getUSD()<0) {
			return 0;
		}
		int i=updateDeposit(d1.getCurrency(),money);
		int i2=detailsMappler.writeWithdrawDetails(d1);
		if(i==1&&i2==1) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public List<Money> queryAllBlanace(){
		return moneyMappler.queryAllBlanace();
	}
	
	@Override
	public int updateMoneyById(Map<String, Object> map) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time=sdf.format(new Date());
		Money m1=new Money();
		try {
			m1.setUid(Integer.valueOf(String.valueOf(map.get("uid"))));
			m1.setCNY(Double.valueOf(String.valueOf(map.get("CNY"))));
			m1.setUSD(Double.valueOf(String.valueOf(map.get("USD"))));
		} catch (Exception e) {
			return 0;
		}
		Details d1=new Details();
		d1.setUid(m1.getUid());
		d1.setDate(Time);
		Money m2=new Money();
		m2=moneyMappler.selectCNYById(m1.getUid());
		Money m3=new Money();
		m3=moneyMappler.selectUSDById(m1.getUid());
		d1.setAmount((m1.getCNY()+m1.getUSD())-(m2.getCNY()+m3.getUSD()));
		d1.setCurrency("CNY/USD");
		d1.setType("3");
		detailsMappler.writeDetails(d1);
		return moneyMappler.updateMoneyById(m1);
	}
	
	int updateDeposit(String currency,Money m) {
		switch (currency){
			case "CNY":{
				int i=moneyMappler.updateCNYById(m);
				if(i==1) {
					return 1;
				}
				return 0;
			}
			case "USD":{
				int i=moneyMappler.updateUSDById(m);
				if(i==1) {
					return 1;
				}
				return 0;
			}
		}
		return 0;
	}
	
	int transferAccounts(Details d1,double from,double to) {
		switch (d1.getCurrency()){
			case "CNY":{
				Money m1=new Money();
				m1.setUid(d1.getUid());
				m1.setCNY(from);
				Money m2=new Money();
				m2.setUid(d1.getToUid());
				m2.setCNY(to);
				int i1=moneyMappler.updateCNYById(m1);
				int i2=moneyMappler.updateCNYById(m2);
				if(i1==1&&i2==1) {
					return 1;
				}
				return 0;
			}
			case "USD":{
				Money m1=new Money();
				m1.setUid(d1.getUid());
				m1.setUSD(from);
				Money m2=new Money();
				m2.setUid(d1.getToUid());
				m2.setUSD(to);
				int i1=moneyMappler.updateUSDById(m1);
				int i2=moneyMappler.updateUSDById(m2);
				if(i1==1&&i2==1) {
					return 1;
				}
				return 0;
			}
		}
		return 0;
	}
}
