package FrontController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.Client;
import servlet.HomeController;
import servlet.Login;

public class Dispatcher {

	public static void myVirtualRouterMethod(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		System.out.println("\n\n\tIN OUR DISPATCHER (myVirtualRouter()) ");

		System.out.println("Current URL: " + req.getRequestURL());
		System.out.println("Current URI: " + req.getRequestURI());
		Client F = new Client();
		HomeController UserHome = new HomeController();
		Login UserLogin = new Login();
		switch (req.getRequestURI()) {

		case "/PROJECT1/Login/Login":
			System.out.println("case 1");
			UserLogin.Login(req, resp);
			break;
		case "/PROJECT1/forwarding/home":
			System.out.println("case 2");
			UserHome.home(req, resp);
			break;
		case "/PROJECT1/Login/getCurrentUser ":
			System.out.println("case 3");
			UserLogin.getCurrentUser(req, resp);
			break;

		case "/PROJECT1/Client/AddingTicket":
			System.out.println("case 4");
			F.AddingTicket(req, resp);
			break;
		case "/PROJECT1/Client/GetAllTickets":
			System.out.println("case 5");
			F.ShowAllTickets(req, resp);
			break;
		case "/PROJECT1/Client/ShowEmployeeticket":
			System.out.println("case 6");
			F.ShowEmployeeticket(req, resp);
			break;

		case "/PROJECT1/forwarding/financial":
			System.out.println("case 8");
			UserHome.Financial(req, resp);
			break;
		case "/PROJECT1/Client/showPendingTicks":
			System.out.println("case 9");
			F.showPendingTicks(req, resp);
			break;

		case "/PROJECT1/Client/showApprovedTicks":
			System.out.println("case 10");
			F.showApprovedTicks(req, resp);
			break;
		case "/PROJECT1/Client/showDeniedTicks":
			System.out.println("case 11");
			F.showDeniedTicks(req, resp);
			break;
		case "/PROJECT1/Client/updateTicket":
			System.out.println("case 12");
			F.updateTicket(req, resp);
			break;
			

		case "/PROJECT1/forwarding/Logout":
			System.out.println("case 13");
			UserHome.Logout(req, resp);
			break;

		default:
			System.out.println(" you gave a bad URL");
			resp.getWriter().println("You've given me a bad URL");
			req.getRequestDispatcher("/HTML/BadLogin.html").forward(req, resp);
		}
	}

}
