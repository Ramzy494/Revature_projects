package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.ERS_REIMBURSEMENT_MODEL;
import Model.ERS_USERS_Model;


public class ERS_REIMBURSEMENT_DAO {

	public static String url= "jdbc:postgresql://";
	public static String username= "";
	public static String password="";

	public static boolean insertTicket(ERS_REIMBURSEMENT_MODEL reimb, ERS_USERS_Model user) {
		Date sqlDate = new Date(0);
		sqlDate = new java.sql.Date(sqlDate.getTime());
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String ourSQLstatement = "INSERT INTO ers_reimbursement VALUES (DEFAULT, " + reimb.getREIMB_AMOUNT() + ", "
					+ "Current_TimeStamp" + ", NULL, '" + reimb.getREIMB_DESCRIPTION() + "' ," + reimb.getREIMB_AUTHOR()
					+ ",NULL," + reimb.getREIMB_STATUS_ID() + "," + "?" + ")";
			System.out.println(ourSQLstatement);

			PreparedStatement ps = conn.prepareStatement(ourSQLstatement);
			ps.setInt(1, reimb.getREIMB_STATUS_ID());
			int numOfRows = ps.executeUpdate();

			if (numOfRows == 1) {
				System.out.println("ticket addded");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<ERS_REIMBURSEMENT_MODEL> GetAllTickets() {
		List<ERS_REIMBURSEMENT_MODEL> Tickets = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String ourSQLStatement = "SELECT * FROM ERS_REIMBURSEMENT";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Tickets.add(new ERS_REIMBURSEMENT_MODEL(rs.getInt("REIM_ID"), rs.getInt("REIMB_AMOUNT"),
						rs.getTimestamp("REIMB_SUBMITTED"), rs.getTimestamp("REIMB_RESOLVED"),
						rs.getString("REIMB_DESCRIPTION"), rs.getInt("REIMB_AUTHOR"), rs.getInt("REIMB_RESOLVER"),
						rs.getInt("REIMB_STATUS_ID"), rs.getInt("REIMB_TYPE_ID")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Tickets;

	}

	public List<ERS_REIMBURSEMENT_MODEL> EmployeeTcket(ERS_USERS_Model user) {
		List<ERS_REIMBURSEMENT_MODEL> Tickets = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String ourSQLStatement = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR=? ";

			PreparedStatement ps = conn.prepareStatement(ourSQLStatement);

			ps.setInt(1, user.getERS_USERS_ID());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Tickets.add(new ERS_REIMBURSEMENT_MODEL(rs.getInt("REIM_ID"), rs.getInt("REIMB_AMOUNT"),
						rs.getTimestamp("REIMB_SUBMITTED"), rs.getTimestamp("REIMB_RESOLVED"),
						rs.getString("REIMB_DESCRIPTION"), rs.getInt("REIMB_AUTHOR"), rs.getInt("REIMB_RESOLVER"),
						rs.getInt("REIMB_STATUS_ID"), rs.getInt("REIMB_TYPE_ID")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Tickets;

	}

	public static List<ERS_REIMBURSEMENT_MODEL> approvedStatus() {
		List<ERS_REIMBURSEMENT_MODEL> appTicket = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String ourSQLstatement = "Select * from ERS_REIMBURSEMENT Where REIMB_STATUS_ID=1";
			PreparedStatement ps = conn.prepareStatement(ourSQLstatement);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				appTicket.add(new ERS_REIMBURSEMENT_MODEL(rs.getInt("reim_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appTicket;

	}

	public static List<ERS_REIMBURSEMENT_MODEL> DeniedStatus() {
		List<ERS_REIMBURSEMENT_MODEL> denTicket = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String ourSQLstatement = "Select * from ERS_REIMBURSEMENT Where REIMB_STATUS_ID=2";
			PreparedStatement ps = conn.prepareStatement(ourSQLstatement);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				denTicket.add(new ERS_REIMBURSEMENT_MODEL(rs.getInt("reim_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return denTicket;

	}

	public static List<ERS_REIMBURSEMENT_MODEL> pedingStatus() {
		List<ERS_REIMBURSEMENT_MODEL> penTicket = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String ourSQLstatement = "Select * from ERS_REIMBURSEMENT Where REIMB_STATUS_ID=3";
			PreparedStatement ps = conn.prepareStatement(ourSQLstatement);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				penTicket.add(new ERS_REIMBURSEMENT_MODEL(rs.getInt("reim_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return penTicket;

	}

	public static void updateTicket1(ERS_REIMBURSEMENT_MODEL reim, ERS_USERS_Model user) {
		String SQL = "UPDATE ers_reimbursement SET reimb_resolved = CURRENT_TIMESTAMP, reimb_resolver = "
				+ user.getUSER_ROLE_ID() + ", reimb_status_id = " + reim.getREIMB_STATUS_ID() + " WHERE reim_id ="
				+ reim.getREIM_ID();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement(SQL);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
