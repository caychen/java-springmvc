package org.com.cay.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestExceptionHandlerExceptionResolver {

	/**
	 * 1����ExceptionHandler����������п��Լ���Exception���͵Ĳ���������Ӧ�ķ������쳣����
	 * 2��@ExceptionHandler������β��ܴ���Map����ϣ�����쳣��Ϣ����ҳ���ϣ���Ҫʹ��ModelAndView��Ϊ��������ֵ
	 * 3��@ExceptionHandler������ǵ��쳣�����ȼ����⣬
	 * 4��@ControllerAdvice������ڵ�ǰHandler���Ҳ���@ExceptionHandler������ʶ�����ĵ�ǰ�������ֵ��쳣
	 * 		���ȥ@ControllerAdvice��ǵ����в���@ExceptionHandler��ǵķ����������쳣
	 * @param ex
	 * @return
	 */
//	@ExceptionHandler(value={RuntimeException.class})
//	public ModelAndView handlerArithmeticException2(Exception ex){
//		System.out.println("[�쳣]: " + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
//	
//	@ExceptionHandler(value={ArithmeticException.class})
//	public ModelAndView handlerArithmeticException(Exception ex){
//		System.out.println("�쳣: " + ex);
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
