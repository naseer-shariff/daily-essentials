package com.dailyessentials.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HiController {

	@GetMapping("/")
	public String Hi()
	{
		return "home";
	}
}
