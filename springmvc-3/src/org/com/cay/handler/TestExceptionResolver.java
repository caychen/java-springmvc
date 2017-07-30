package org.com.cay.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestExceptionResolver {

	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
		if(i == 13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver...");
		return "success";
	}
	
	@RequestMapping(value="/testDefaultHandlerExceptionResolver",method=RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("testDefaultHandlerExceptionResolver...");
		return "success";
	}
	
	@RequestMapping("/testSimpleMappingException")
	public String testSimpleMappingException(@RequestParam("i") int i){
		String[] vals = new String[10];
		System.out.println(vals[i]);
		return "success";
	}
}
