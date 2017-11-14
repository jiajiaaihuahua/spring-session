package online.shixun.project.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import online.shixun.project.util.HttpRequest;

@Controller
public class WXContorller {
	
	private String appid = "wxd0adcc832d170009";
	private String secret = "9bfb1e80fa4fffc7e88bb742087b0025";
	
	@RequestMapping(path="/callback",method=RequestMethod.GET)
	public String callback(@RequestParam(value="code",required=false ) String code,Model model) throws UnsupportedEncodingException{
			System.out.println("code 的值是 "+code);
			
			//通过code换取网页授权access_token
			String base_url = "https://api.weixin.qq.com/sns/oauth2/access_token?";
			
			String result = HttpRequest.sendPost(base_url+"appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code", "");
			JSONObject json =JSONObject.parseObject(result);
			String access_token = json.getString("access_token");
			String openid = json.getString("openid");
			
			String user_info = HttpRequest.sendGet("https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN","");
			JSONObject user_info_json =JSONObject.parseObject(user_info);
			
			model.addAttribute("appid", appid);
			model.addAttribute("redirect_uri", URLEncoder.encode("http://liusijia.iok.la/spring-cache/wx_login", "utf-8"));
			return "wx_login";
	}
	
	@RequestMapping(path="/to_wx_login",method=RequestMethod.GET)
	public String to_wx_login(Model model) throws UnsupportedEncodingException{
		model.addAttribute("appid", appid);
		model.addAttribute("redirect_uri", URLEncoder.encode("http://liusijia.iok.la/spring-cache/wx_login", "utf-8"));
		return "wx_login";
	}
	
	@RequestMapping(path="/wx_login",method=RequestMethod.GET)
	public String wx_login(Model model){
		return "wx_success";
	}
	
	

}
