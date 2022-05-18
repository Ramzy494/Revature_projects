package project.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import project.model.AccountModel;
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
	/**
	 * Controller that takes in form data to create new accounts and adds them to
	 * the database
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
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

	/**
	 * Controller that will update a profile from the session using data from AJAX
	 * 
	 * @param ses
	 * @param user
	 * @return
	 */
	@PostMapping("/updateProfile")
	public @ResponseBody AccountModel updateProfile(HttpSession ses, @RequestBody AccountModel user) {

		AccountModel updatedCurrentUser = null;
		AccountModel currentUser = (AccountModel) ses.getAttribute("currentUser");

		updatedCurrentUser = accService.updateProfile(user, currentUser);

		if (updatedCurrentUser != null) {
			ses.setAttribute("currentUser", updatedCurrentUser);

		}

		return updatedCurrentUser;
	}

	/**
	 * Controller that pulls the user from the session in order to DOM manipulate the front end accordingly
	 * @param Sesh
	 * @return
	 */
	@GetMapping("/ReadUser")
	public @ResponseBody AccountModel User(HttpSession Sesh) {

		AccountModel User = (AccountModel) Sesh.getAttribute("currentUser");
//		System.out.println(User);

		return User;

	}

}
