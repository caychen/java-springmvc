package org.com.cay.handler;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testLocaleResolver {

//	@Autowired
//	private ResourceBundleMessageSource messageSource;
//	
//	@RequestMapping("/i18n")
//	public String testI18n(Locale locale){
//		String message = messageSource.getMessage("i18n.username", null, locale);
//		System.out.println(message);
//		return "i18n";
//	}
}
