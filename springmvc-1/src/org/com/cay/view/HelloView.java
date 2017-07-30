package org.com.cay.view;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component
public class HelloView implements View {

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> arg0, HttpServletRequest arg1, HttpServletResponse arg2) throws Exception {
		// TODO Auto-generated method stub
		arg2.getWriter().print("hello view, time: " + new Date());
	}

}
