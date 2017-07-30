package org.com.cay.handler;

import org.com.cay.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestBindingResult {

	@RequestMapping("/testBindingResult")
	//如果类型转换出错或者格式化出错，则会把错误信息放入BindingResult对象中
	public String testBindingResult(Employee employee, BindingResult result){
		if(result.getErrorCount() > 0){
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			
			//验证出错，则转向定制的页面
			return "error";
		}
		
		System.out.println(employee);
		return "success";
	}
}
