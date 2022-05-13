package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.ERS_USERS_Model;
import Model.ERS_USER_ROLES_Model;
import DAO.UserDAO;

public class Login extends HttpServlet {
	
	ERS_USERS_Model user = new ERS_USERS_Model();

	public static void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String myPath = null;

	if(!req.getMethod().equals("POST")) {
		myPath = "/index.html";
		req.getRequestDispatcher(myPath).forward(req, resp);
	}
	ERS_USERS_Model user = new ERS_USERS_Model();
	
	
	user.setERS_USERNAME(req.getParameter("theirUsername"));
	user.setERS_PASSWORD(req.getParameter("theirPassword"));
		


	if(UserDAO.Loginverify(user)) {
		ERS_USERS_Model CurrentUser = UserDAO.Session(user);
		req.getSession().setAttribute("currentUser",CurrentUser);
		System.out.println(CurrentUser);
		if(CurrentUser.getUSER_ROLE_ID()==1) {
			myPath="/forwarding/home";
			req.getRequestDispatcher(myPath).forward(req,resp);
		}else if(CurrentUser.getUSER_ROLE_ID()==2) {
			myPath="/forwarding/financial";
			req.getRequestDispatcher(myPath).forward(req,resp);
		}
		
	}else {
		myPath="/forward/BadLogin";
		req.getRequestDispatcher(myPath).forward(req,resp);
	}
	}
public static void getCurrentUser (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ERS_USER_ROLES_Model currentUser = (ERS_USER_ROLES_Model)req.getSession().getAttribute("currentUser");
		
		PrintWriter printer = resp.getWriter();
		printer.write(new ObjectMapper().writeValueAsString(currentUser));
		
		
	}


	
}