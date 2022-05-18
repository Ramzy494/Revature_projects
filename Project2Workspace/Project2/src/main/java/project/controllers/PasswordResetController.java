package project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import project.service.AccountService;
import project.service.EmailService;


@Controller
public class PasswordResetController {

	/// Fields
	private EmailService emailService;
	private AccountService accService;

	/// Constructors
	// No Args
	public PasswordResetController() {

	}

	// All Args
	@Autowired
	public PasswordResetController(EmailService emailService, AccountService accService) {
		super();
		this.emailService = emailService;
		this.accService = accService;
	}

	/**
	 * Controller that takes in form data to eventually send an email to the user so as to reset their password
	 * @param req
	 * @param resp
	 * @return
	 */
	@PostMapping(value = "/resetpasswordemail")
	public String resetPasswordEmail(HttpServletRequest req, HttpServletResponse resp) {

		String email = req.getParameter("email");
		System.out.println(email);
		
		emailService.sendMessage(email);
	
		return "redirect:/html/reset-password.html";
		
	}
		/**
		 * Controller that takes in form data to reset a password based on the email of the user and a given temporary hashed password
		 * @param req
		 * @param resp
		 * @return
		 */
	@PostMapping(value="/resetpassword")
	public String resetPassword(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String tempPassword = req.getParameter("tempPassword");
		String password = req.getParameter("password");
		
		accService.updatePassword(email, tempPassword, password);
		
		return "redirect:/html/login.html";
	}
	

}
