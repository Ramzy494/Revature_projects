package Model;

public class ERS_USERS_Model {
	
	int ERS_USERS_ID;
	static String ERS_USERNAME;
	static String ERS_PASSWORD;
	String USERS_FIRST_NAME;
	String USERS_LAST_NAME;
	 String USER_EMAIL;
	int USER_ROLE_ID;
	
	public ERS_USERS_Model(int eRS_USERS_ID, String eRS_USERNAME, String eRS_PASSWORD, String uSERS_FIRST_NAME,
			String uSERS_LAST_NAME, String uSER_EMAIL, int uSER_ROLE_ID) {
		super();
		ERS_USERS_ID = eRS_USERS_ID;
		ERS_USERNAME = eRS_USERNAME;
		ERS_PASSWORD = eRS_PASSWORD;
		USERS_FIRST_NAME = uSERS_FIRST_NAME;
		USERS_LAST_NAME = uSERS_LAST_NAME;
		USER_EMAIL = uSER_EMAIL;
		USER_ROLE_ID = uSER_ROLE_ID;
	}
	
	public ERS_USERS_Model() {
		// TODO Auto-generated constructor stub
	}

	public int getERS_USERS_ID() {
		return ERS_USERS_ID;
	}
	public void setERS_USERS_ID(int eRS_USERS_ID) {
		ERS_USERS_ID = eRS_USERS_ID;
	}
	public static String getERS_USERNAME() {
		return ERS_USERNAME;
	}
	public void setERS_USERNAME(String eRS_USERNAME) {
		ERS_USERNAME = eRS_USERNAME;
	}
	public static String getERS_PASSWORD() {
		return ERS_PASSWORD;
	}
	public void setERS_PASSWORD(String eRS_PASSWORD) {
		ERS_PASSWORD = eRS_PASSWORD;
	}
	public String getUSERS_FIRST_NAME() {
		return USERS_FIRST_NAME;
	}
	public void setUSERS_FIRST_NAME(String uSERS_FIRST_NAME) {
		USERS_FIRST_NAME = uSERS_FIRST_NAME;
	}
	public String getUSERS_LAST_NAME() {
		return USERS_LAST_NAME;
	}
	public void setUSERS_LAST_NAME(String uSERS_LAST_NAME) {
		USERS_LAST_NAME = uSERS_LAST_NAME;
	}
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	public int getUSER_ROLE_ID() {
		return USER_ROLE_ID;
	}
	public void setUSER_ROLE_ID(int uSER_ROLE_ID) {
		USER_ROLE_ID = uSER_ROLE_ID;
	}
	
	@Override
	public String toString() {
		return "ERS_USERS [ERS_USERS_ID=" + ERS_USERS_ID + ", ERS_USERNAME=" + ERS_USERNAME + ", ERS_PASSWORD="
				+ ERS_PASSWORD + ", USERS_FIRST_NAME=" + USERS_FIRST_NAME + ", USERS_LAST_NAME=" + USERS_LAST_NAME
				+ ", USER_EMAIL=" + USER_EMAIL + ", USER_ROLE_ID=" + USER_ROLE_ID + "]";
	}
	
}
