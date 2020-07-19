package cc.asjks.bms.server.service;

import java.util.List;
import java.util.Map;

import cc.asjks.bms.server.model.*;

public interface DetailsService {
	List<Details> queryDetails(Map<String, Object> map);
	List<Details> queryAllDetails();
}
