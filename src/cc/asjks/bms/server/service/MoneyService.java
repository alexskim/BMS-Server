package cc.asjks.bms.server.service;

import java.util.List;
import java.util.Map;

import cc.asjks.bms.server.model.*;

public interface MoneyService {
	Double queryBlanaceById(Map<String, Object> map);
	List<Money> queryAllBlanace();
	int transferAccountsById(Map<String, Object> map);
	int depositById(Map<String, Object> map);
	int withdrawById(Map<String, Object> map);
	int updateMoneyById(Map<String, Object> map);
}
