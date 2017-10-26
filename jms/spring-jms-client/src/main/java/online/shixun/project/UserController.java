package online.shixun.project;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Resource
	private CheckingAccountService checkingAccountService;
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	public String getUser(ModelMap model){
		List<Goods> list = checkingAccountService.getGoodsList();
		model.addAttribute("goodslist",list);
		return "getUser";
	}

}
