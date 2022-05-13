package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

	public static void home (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myPath = "/HTML/Home.html";
		
		req.getRequestDispatcher(myPath).forward(req, resp);
	}

	
	public static void Financial (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myPath = "/HTML/Financial.html";
		
		req.getRequestDispatcher(myPath).forward(req, resp);
	}
	public static void Logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String myPath="/HTML/Index.html";
	req.getRequestDispatcher(myPath).forward(req, resp);
	}
}

