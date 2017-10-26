package online.shixun.project;

import java.util.List;

public interface CheckingAccountService {

	public String cancelAccount(Long accountId);
	
	public List addAccount(Long accountId);
	
	/**
	 * 获取所有商品
	 */
	public List<Goods> getGoodsList();

}