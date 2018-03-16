package online.shixun.demo.robot;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import online.shixun.demo.robot.dao.MessageDao;
import online.shixun.demo.robot.dao.UserDao;
import online.shixun.demo.robot.dto.Message;
import online.shixun.demo.robot.dto.User;
import online.shixun.demo.robot.service.UserMessageService;

public class UserTest extends BaseUtilTest{

	@Autowired
	private UserDao userDao ;
	
	@Autowired
	private MessageDao messageDao ;
	
	@Autowired
	private UserMessageService userMessageService ;
	
	@Test
	public void getUserTest(){
		List<User>  list = userDao.getUser();
		for(User user :list){
			System.out.println(user.getName());
		}
	}
	
	@Test
	public void sendMessageTest(){
		//	userInfo
		JSONObject userInfo = new JSONObject();
		userInfo.put("apiKey", "da772444a62343e1ad1f2df4f6255b93");
		userInfo.put("userId", "123");
		
		//	inputText 输入的文本
		JSONObject inputText = new JSONObject();
		inputText.put("text", "刘思嘉");
		
		//	location 
		JSONObject location = new JSONObject();
		location.put("city", "湖北");
		location.put("province", "武汉");
		location.put("street", "光谷三路");
		
		//	selfInfo 
		JSONObject selfInfo = new JSONObject();
		selfInfo.put("location", location);
		
		//	perception 
		JSONObject perception = new JSONObject();
		perception.put("selfInfo", selfInfo);
		perception.put("inputText", inputText);
		
		JSONObject json = new JSONObject();
		json.put("reqType", 0);
		json.put("perception", perception);
		json.put("userInfo",userInfo);
		System.out.println(json.toJSONString());
		String result = userMessageService.sendUserMessage(json.toJSONString(),1L);
		System.out.println(result);
	}
	
	@Test
	public void saveUserMessage(){
		Message message = new Message();
		message.setId(1L);
		message.setReplyContent("回复的内容");
		message.setSendContent("发送的内容");
		messageDao.saveMessage(message);
	}
	
	@Test
	public void getUserById(){
		User u = userDao.getUserById(1L);
		System.out.println(u);
		
	}
	
}
