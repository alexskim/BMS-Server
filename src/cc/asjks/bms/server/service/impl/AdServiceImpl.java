package cc.asjks.bms.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.Blob;

import cc.asjks.bms.server.mapper.*;
import cc.asjks.bms.server.model.*;
import cc.asjks.bms.server.service.*;

@Service("adService")
public class AdServiceImpl implements AdService{
	@Autowired
	AdMapper adMapper;
	
	@Autowired
	AdService adService;
	
	@Override
	public List<Ad> queryAllAd() {
		return adMapper.queryAllAd();
	}
	@Override
	public List<Ad> queryAllAdByTime(long nowTimestamp) {
		return adMapper.queryAllAdByTime(nowTimestamp);
	}
	@Override
	public int updateAd(Map<String, Object> map) {
		Ad ad1=new Ad();
		try {
			ad1.setId(Integer.valueOf(String.valueOf(map.get("id"))));
			ad1.setContact(String.valueOf(map.get("contact")));
			ad1.setContent(String.valueOf(map.get("content")));
			ad1.setImg(String.valueOf(map.get("img")));
			ad1.setBegin(Long.valueOf(String.valueOf(map.get("begin"))));
			ad1.setEnd(Long.valueOf(String.valueOf(map.get("end"))));
		} catch (Exception e) {
			return 0;
		}
		return adMapper.updateAd(ad1);
	}
	@Override
	public int addAd(Map<String, Object> map) {
		Ad ad1=new Ad();
		try {
			ad1.setContact(String.valueOf(map.get("contact")));
			ad1.setContent(String.valueOf(map.get("content")));
			//ad1.setImg(Byte.valueOf((byte) map.get("img")));
			ad1.setBegin(Long.valueOf(String.valueOf(map.get("begin"))));
			ad1.setEnd(Long.valueOf(String.valueOf(map.get("end"))));
		} catch (Exception e) {
			return 0;
		}
		return adMapper.addAd(ad1);
	}
	@Override
	public int deleteAdById(Map<String, Object> map) {
		return adMapper.deleteAdById(Integer.valueOf(String.valueOf(map.get("id"))));
	}

}
