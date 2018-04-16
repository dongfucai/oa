package com.tz.oa.sysmanage.controller;



import javax.servlet.http.HttpSession;

import com.tz.oa.sysmanage.entity.User;
import com.tz.oa.sysmanage.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 用于注解开发的LoginController
 * @author dongfucai
 *
 */
@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService iUserService;

	@RequestMapping("/gotoregister")
	public String gotoregiter(){
		return "register";
	}
	@RequestMapping("/gotoLogin")
	public String gotoLogin(){
		String ss="dffd";
		return "login";

	}

	@RequestMapping("/register")
	public String register(@RequestParam(value = "",required = true) String loginName,
						   String password, String password2,Model model,HttpSession session){

		System.out.println(loginName);
		System.out.println(password);

		if(loginName.isEmpty()||password.isEmpty()){
			model.addAttribute("loginFlag","用户名或者密码为空");
		}else if(!password.equals(password2)){
			model.addAttribute("loginFlag","两次输入密码不一样");
		}else {

			int res = iUserService.selectbyname(loginName);
			System.out.println("用户名查询:"+res);
			if (res >= 1) {
				model.addAttribute("loginFlag", "用户名重名，请再次输入");
				return "main";
			}
			res = iUserService.insert(loginName, password);
			System.out.println(res);

			if (res == 1) {
				model.addAttribute("loginFlag", "注册成功");

			} else {
				model.addAttribute("loginFlag", "注册失败");
			}
		}
		return "main";

	}

	@RequestMapping("/login")
	public String login(@RequestParam(value="loginName" ,required=true) String loginName,
						String password,Model model,HttpSession session){

		System.out.println(loginName);
		System.out.println(password);
		int res=iUserService.logintest(loginName,password);
		System.out.println("result:====  "+res);
		if(res==0){
			model.addAttribute("loginFlag","登陆失败");
		}else{
			model.addAttribute("loginFlag","登陆成功");
		}

		return "main";
	//	return "redirect:/main" ;
/*
		//用redirect和forward标签返回
		//注意:用这两个标签必须写全路径,不适应视图解析的逻辑视图
		if(StringUtils.isNotEmpty(loginName)&&
				StringUtils.isNotEmpty(password)){
			User user = iUserService.loginUser(loginName, password);
			if(user!=null){
				logger.info("登陆成功");
				session.setAttribute("user", user);
				return "redirect:/main" ;
			}else{
				model.addAttribute("loginFlag","登陆失败,请输入正确的用户名和密码");
				return "forward:/WEB-INF/pages/login.jsp";
			}
		}else{
			model.addAttribute("loginFlag","登陆失败,请输入正确的用户名和密码");
			return "forward:/WEB-INF/pages/login.jsp";
		}*/

	}

	@RequestMapping("/main")
	public String main(){
		return "main/main";
	}

}
