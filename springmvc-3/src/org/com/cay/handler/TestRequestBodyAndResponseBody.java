package org.com.cay.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.com.cay.dao.EmployeeDao;
import org.com.cay.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestRequestBodyAndResponseBody {

	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value="/testjson",produces={"application/json;charset=UTF-8"})
	public @ResponseBody Collection<Employee> getAllEmployee(){
		return employeeDao.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestParam("desc")String desc, @RequestBody String body){
		System.out.println(body);
		System.out.println("----------------");
		System.out.println(desc);
		return "Hello :" + new Date();
	}
	
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte[] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream is = servletContext.getResourceAsStream("/js/jquery-3.1.1.min.js");
		body = new byte[is.available()];
		is.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=jquery-3.1.1.min.js");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body,headers,statusCode);
		
		return response;
	}
}
