package org.com.cay.controller;

import org.com.cay.entity.User;
import org.com.cay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String name, @RequestParam("password") String pwd){
		ModelAndView mv = new ModelAndView();
		
		User user = userService.getByUsernameAndPwd(name, pwd);
		if(user == null){
			
		}else{
			mv.addObject("username", name);
			mv.setViewName("success");
		}
		return mv;
	}
}
