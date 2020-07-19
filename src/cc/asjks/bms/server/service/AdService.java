package cc.asjks.bms.server.service;

import java.util.List;
import java.util.Map;

import cc.asjks.bms.server.model.*;

public interface AdService {
	List<Ad> queryAllAd();
	List<Ad> queryAllAdByTime(long nowTimestamp);
	int updateAd(Map<String, Object> map);
	int addAd(Map<String, Object> map);
	int deleteAdById(Map<String, Object> map);
}
