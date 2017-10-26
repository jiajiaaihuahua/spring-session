package online.shixun.project;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "client.xml", "jms.xml" });
		CheckingAccountService service = (CheckingAccountService) ctx.getBean("checkingAccountService");
		String str = service.cancelAccount(new Long(10));
		System.out.println(str);
		
		List<String> list = service.addAccount(1L);
		System.out.println(list);
	}

}
