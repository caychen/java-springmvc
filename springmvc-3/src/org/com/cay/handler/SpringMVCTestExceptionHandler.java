package org.com.cay.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SpringMVCTestExceptionHandler {

	@ExceptionHandler(value={ArithmeticException.class})
	public ModelAndView handlerArithmeticException(Exception ex){
		System.out.println("“Ï≥£: " + ex);
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		return mv;
	}
}
