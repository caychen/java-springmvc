package org.com.cay.handler;

import org.com.cay.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestEmployeeConverter {

	@RequestMapping("/testEmployeeConverter")
	public String testEmployeeConverter(Employee employee){
		System.out.println(employee);
		return "success";
	}
	
	
}
