package project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import project.model.AccountModel;
import project.service.AccountService;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private RedirectStrategy redirect;
	
	private String homePage = "/html/home.html";
	
	/**
	 * Retrieves the currently authenticated user from the database and stores their account object in the session.
	 * Redirects the user to the home page once complete.
	 * 
	 * @author Jake
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// Storing user object in session
		String username = authentication.getName();
		AccountModel user = service.getAccountByUsername(username);
		request.getSession().setAttribute("currentUser", user);
		
		// Redirect user to the home page
		redirect.sendRedirect(request, response, homePage);
		clearAuthenticationAttributes(request);
		
	}
	
}