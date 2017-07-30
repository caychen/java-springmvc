package org.com.cay.controller;

import javax.servlet.http.HttpServletRequest;

import org.com.cay.entity.User;
import org.com.cay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.internal.logging.Logger;

@Controller
@Scope("prototype")
public class UserController {

	@Autowired
	private IUserService userService;
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@RequestMapping("/forLogin")
	public ModelAndView login(HttpServletRequest request, @RequestParam("username")String name, @RequestParam("password")String pwd){
		logger.info(name);
		ModelAndView mv = new ModelAndView();
		User u = userService.login(name, pwd);
		if(u != null){
			request.setAttribute("id", u.getId());//放入request
			request.getSession().setAttribute("username", name);//放入session
			mv.addObject("password", pwd);//放入mv
		}
		mv.setViewName("login");
		logger.info("viewname: " + mv.getViewName());
		return mv;
	}
}
