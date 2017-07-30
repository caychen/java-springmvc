package org.com.cay.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestExceptionHandlerExceptionResolver {

	/**
	 * 1、在ExceptionHandler方法的入参中可以加入Exception类型的参数，即对应的发生的异常对象
	 * 2、@ExceptionHandler方法入参不能传入Map，若希望把异常信息传到页面上，需要使用ModelAndView作为函数返回值
	 * 3、@ExceptionHandler方法标记的异常有优先级问题，
	 * 4、@ControllerAdvice：如果在当前Handler中找不到@ExceptionHandler方法标识出来的当前方法出现的异常
	 * 		则会去@ControllerAdvice标记的类中查找@ExceptionHandler标记的方法来处理异常
	 * @param ex
	 * @return
	 */
//	@ExceptionHandler(value={RuntimeException.class})
//	public ModelAndView handlerArithmeticException2(Exception ex){
//		System.out.println("[异常]: " + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
//	
//	@ExceptionHandler(value={ArithmeticException.class})
//	public ModelAndView handlerArithmeticException(Exception ex){
//		System.out.println("异常: " + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
	
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i){
		System.out.println("result: " + (10 / i));
		return "success";
	}
}
