package org.com.cay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	private static final String SUCCESS = "success";

	@RequestMapping(value={"/helloworld"})
	public String hello(){
		System.out.println("helloworld");
		return SUCCESS;
	}
}
