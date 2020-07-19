package cc.asjks.bms.server.mapper;

import java.util.List;

import cc.asjks.bms.server.model.*;

public interface DetailsMapper {
	public int writeDetails(Details d1);
	public int writeDepositDetails(Details d1);
	public int writeWithdrawDetails(Details d1);
	public List<Details> queryDetails(Integer uid);
	public List<Details> queryAllDetails();
}
