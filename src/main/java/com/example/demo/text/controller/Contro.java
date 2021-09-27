package com.example.demo.text.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class Contro {
	
	@RequestMapping(value = "/home")
	public String home(Model model){
		return "hh";
	}
	@ResponseBody
	@RequestMapping("/valueTest")
	public String valueTest() {
		String value ="테스트 String";
		return value;
			
	}
}
