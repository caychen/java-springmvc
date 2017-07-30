package org.com.cay.handler;

import org.com.cay.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestBindingResult {

	@RequestMapping("/testBindingResult")
	//�������ת��������߸�ʽ���������Ѵ�����Ϣ����BindingResult������
	public String testBindingResult(Employee employee, BindingResult result){
		if(result.getErrorCount() > 0){
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			
			//��֤������ת���Ƶ�ҳ��
			return "error";
		}
		
		System.out.println(employee);
		return "success";
	}
}
