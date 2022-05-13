package project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import project.service.AccountService;

@Controller
public class RegisterAccountController {

	/// Fields
	private AccountService accService;

	/// Constructors
		// No Args
	public RegisterAccountController() {
	}

		// All Args
	@Autowired
	public RegisterAccountController(AccountService accService) {
		super();
		this.accService = accService;
	}

	/// End Point
	@PostMapping(value = "/registeraccount")
	public String registerAccount(HttpServletRequest req, HttpServletResponse resp) {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		
		
		accService.registerAccount(username, password, firstName, lastName, email);
		
		return "redirect:/html/login.html";
		
	}

}
