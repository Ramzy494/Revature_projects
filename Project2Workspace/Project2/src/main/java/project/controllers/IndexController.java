package project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	/**
	 * Controller that sets the index of a call to "/" to go to the login page
	 * 
	 */
	public String index() {
		return "redirect:/html/login.html";
	}
	
}
