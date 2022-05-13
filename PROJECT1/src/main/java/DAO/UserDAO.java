package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ERS_USERS_Model;


public class UserDAO {
	public static String url= "jdbc:postgresql://";
	public static String username= "";
	public static String password="";

	
		public static boolean  Loginverify(ERS_USERS_Model User) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String SQL = " Select * from ers_users where ers_username= '" + ERS_USERS_Model.getERS_USERNAME() + "' AND ers_password='"+ERS_USERS_Model.getERS_PASSWORD()+"'";

			PreparedStatement ps = conn.prepareStatement(SQL);
			System.out.println(SQL);
			ResultSet p= ps.executeQuery();
			while (p.next()){
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	} 
		public static ERS_USERS_Model  Session(ERS_USERS_Model User) {
			try (Connection conn = DriverManager.getConnection(url, username, password)) {
				String SQL = " Select * from ers_users where ers_username= '" + ERS_USERS_Model.getERS_USERNAME() + "' AND ers_password='"+ERS_USERS_Model.getERS_PASSWORD()+"'";

				PreparedStatement ps = conn.prepareStatement(SQL);
				System.out.println(SQL);
				ResultSet p= ps.executeQuery();
				
				while (p.next()){
					ERS_USERS_Model R= new ERS_USERS_Model(p.getInt("ERS_USERS_ID"),p.getString("ERS_USERNAME"),p.getString("ERS_PASSWORD"),p.getString("USERS_FIRST_NAME"),p.getString("USERS_LAST_NAME"),p.getString("USER_EMAIL"),p.getInt("USER_ROLE_ID"));
					return R;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		} 
}

	
