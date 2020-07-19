package cc.asjks.bms.server.mapper;

import java.util.List;

import cc.asjks.bms.server.model.*;

public interface MoneyMapper {

	public Money selectCNYById(int id);
	public Money selectUSDById(int id);
	public int updateCNYById(Money money);
	public int updateUSDById(Money money);
	public List<Money> queryAllBlanace();
	public int updateMoneyById(Money money);
}
