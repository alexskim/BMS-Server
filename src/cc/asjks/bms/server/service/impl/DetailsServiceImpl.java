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

@Service("detailsService")
public class DetailsServiceImpl implements DetailsService {
	@Autowired
	DetailsMapper detailsMapper;
	
	@Autowired
	DetailsService detailsService;
	
	@Override
	public List<Details> queryDetails(Map<String, Object> map){
		return detailsMapper.queryDetails(Integer.valueOf(String.valueOf(map.get("uid"))));
	}
	@Override
	public List<Details> queryAllDetails(){
		return detailsMapper.queryAllDetails();
	}
}
