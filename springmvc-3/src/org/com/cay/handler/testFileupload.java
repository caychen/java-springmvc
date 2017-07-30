package org.com.cay.handler;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class testFileupload {

	@RequestMapping("/testFileupload")
	public String testCommonsFileupload(@RequestParam("desc") String desc,@RequestParam("file") MultipartFile file) throws IOException{
		System.out.println("desc: " + desc);
		System.out.println("origin: " + file.getOriginalFilename());
		System.out.println("inputstream: " + file.getInputStream());
		return "success";
	}
}
