package org.com.cay.handler;

import java.util.Map;

import javax.validation.Valid;

import org.com.cay.dao.DepartmentDao;
import org.com.cay.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestHibernateValidator {

	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 
	 * @param employee:
	 * @param result:
	 * 	该类型必须为BindingResult或者Errors
	 * 	注意：需校验的Bean对象和其绑定结果对象或错误对象时是成对出现的，它们中间不允许声明其他的入参
	 * 	例如：public String xxxx(@Valid User user, BindingResult(Errors) userErrors, 
	 * 							@Valid Dept dept, BindingResult(Errors) deptErrors 
	 * @return
	 */
	
	@RequestMapping("/testJSR303Validator")
	public String testJSR303Validator(@Valid Employee employee, BindingResult result,Map<String, Object>map){
		if(result.getErrorCount() > 0){
			for(FieldError error : result.getFieldErrors())
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			
			//map.put("departments", departmentDao.getDepartments());
			return "index3";
		}
		
		System.out.println(employee);
		return "success";
	}
}
