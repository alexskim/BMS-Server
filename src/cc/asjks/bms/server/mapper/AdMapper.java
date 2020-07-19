package cc.asjks.bms.server.mapper;

import java.util.List;

import cc.asjks.bms.server.model.*;

public interface AdMapper {
	public List<Ad> queryAllAd();
	public List<Ad> queryAllAdByTime(long nowTimestamp);
	public int updateAd(Ad ad1);
	public int addAd(Ad ad1);
	public int deleteAdById(int id);
}
