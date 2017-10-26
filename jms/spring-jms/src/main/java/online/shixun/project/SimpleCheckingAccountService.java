package online.shixun.project;

import java.util.ArrayList;
import java.util.List;

public class SimpleCheckingAccountService implements CheckingAccountService {

	@Override
	public String cancelAccount(Long accountId) {
        System.out.println("Cancelling account [" + accountId + "]");
        return "���accountid��"+accountId;
    }
	
	@Override
	public List addAccount(Long accountId) {
        System.out.println("return list...");
        List<String> list = new ArrayList<String>();
        list.add("liusijia");
        return list;
    }
	
	public List<Goods> getGoodsList(){
		List<Goods> list = new ArrayList<Goods>();
		list.add(new Goods(1L,"盼盼法式小面包","零食"));
		list.add(new Goods(2L,"海天酱油","喝的"));
		list.add(new Goods(2L,"hp暗夜精灵","电子产品"));
		return list;
	}

}
