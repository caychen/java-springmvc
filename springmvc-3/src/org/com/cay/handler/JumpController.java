package org.com.cay.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpController {

	@RequestMapping("/jump")
	public String jump(){
		return "index3";
	}
}
