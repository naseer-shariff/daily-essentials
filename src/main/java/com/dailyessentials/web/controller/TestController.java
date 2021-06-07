package com.dailyessentials.web.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/nm/")
	public String GetHi(Model model) {
		 model.addAttribute("koko", "koko_kolli");
		// model.addAttribute("holas", Arrays.asList(new Hola("Cola", 35), new
		// Hola("Chola", 420)));
		return "Test1";
	}

}
