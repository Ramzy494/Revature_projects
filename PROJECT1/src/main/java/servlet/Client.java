package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import DAO.ERS_REIMBURSEMENT_DAO;
import Model.ERS_REIMBURSEMENT_MODEL;
import Model.ERS_USERS_Model;
import Model.ERS_USER_ROLES_Model;




public class Client  extends HttpServlet{
	public static void AddingTicket (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ERS_USERS_Model currentUser = (ERS_USERS_Model)req.getSession().getAttribute("loggedInUser");
		ObjectMapper mapper = new ObjectMapper();
		int amount= Integer.parseInt(req.getParameter("theAmount"));
		String description= req.getParameter("theDescription");
		int id= Integer.parseInt(req.getParameter("theId"));
		ERS_REIMBURSEMENT_MODEL reim= new ERS_REIMBURSEMENT_MODEL(id,amount ,description);
		System.out.println(reim);
		
		ERS_REIMBURSEMENT_DAO.insertTicket(reim, currentUser);
	

		
	}
	
	static ERS_REIMBURSEMENT_DAO T = new ERS_REIMBURSEMENT_DAO();
	
	
	public static void ShowAllTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<ERS_REIMBURSEMENT_MODEL> listOfTickets = new ArrayList<>();
		listOfTickets = T.GetAllTickets();
		System.out.println("it works");
		System.out.println(listOfTickets);
		
		resp.setContentType("application/json");
		PrintWriter print = resp.getWriter();
		String H=new ObjectMapper().writeValueAsString(listOfTickets); 
		print.write(H);
	}
	public static void ShowEmployeeticket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<ERS_REIMBURSEMENT_MODEL> listOfTickets = new ArrayList<>();
		ERS_USERS_Model H= (ERS_USERS_Model)req.getSession().getAttribute("Currentuser");
		
		listOfTickets = T.EmployeeTcket(H);
		System.out.println("it works");
		System.out.println(listOfTickets);
		
		resp.setContentType("application/json");
		PrintWriter print = resp.getWriter();
		print.write(new ObjectMapper().writeValueAsString(listOfTickets));
	}
	
	public void showPendingTicks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ERS_REIMBURSEMENT_MODEL> pend = new ArrayList<>();
		pend = ERS_REIMBURSEMENT_DAO.pedingStatus();
		
		resp.setContentType("application/json");
		PrintWriter print = resp.getWriter();
		print.write(new ObjectMapper().writeValueAsString(pend));
	}
	public void showApprovedTicks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ERS_REIMBURSEMENT_MODEL> Approved = new ArrayList<>();
		Approved = ERS_REIMBURSEMENT_DAO.approvedStatus();
		
		resp.setContentType("application/json");
		PrintWriter print = resp.getWriter();
		print.write(new ObjectMapper().writeValueAsString(Approved));
	}
	public void showDeniedTicks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ERS_REIMBURSEMENT_MODEL> Denied = new ArrayList<>();
		Denied = ERS_REIMBURSEMENT_DAO.DeniedStatus();
		
		resp.setContentType("application/json");
		PrintWriter print = resp.getWriter();
		print.write(new ObjectMapper().writeValueAsString(Denied));
	}
	
	public static void updateTicket(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ERS_USERS_Model current = (ERS_USERS_Model)req.getSession().getAttribute("currentUser");
		ObjectMapper map = new ObjectMapper();
		int reimID= Integer.parseInt(req.getParameter("rEIM_ID"));
		int statusID= Integer.parseInt(req.getParameter("rEIMB_STATUS_ID"));
		
		ERS_REIMBURSEMENT_MODEL H = new ERS_REIMBURSEMENT_MODEL(reimID,statusID);
		ERS_REIMBURSEMENT_DAO.updateTicket1(H, current);
	}
}
