package Model;

public class ERS_REIMBURSEMENT_TYPE_Model {
	
	int REIMB_TYPE_ID;
	String REIMB_TYPE ;

	public ERS_REIMBURSEMENT_TYPE_Model(int rEIMB_TYPE_ID, String rEIMB_TYPE) {
		super();
		REIMB_TYPE_ID = rEIMB_TYPE_ID;
		REIMB_TYPE = rEIMB_TYPE;
	}
	
	
	public int getREIMB_TYPE_ID() {
		return REIMB_TYPE_ID;
	}
	public void setREIMB_TYPE_ID(int rEIMB_TYPE_ID) {
		REIMB_TYPE_ID = rEIMB_TYPE_ID;
	}
	public String getREIMB_TYPE() {
		return REIMB_TYPE;
	}
	public void setREIMB_TYPE(String rEIMB_TYPE) {
		REIMB_TYPE = rEIMB_TYPE;
	}
	@Override
	public String toString() {
		return "ERS_REIMBURSEMENT_TYPE_Model [REIMB_TYPE_ID=" + REIMB_TYPE_ID + ", REIMB_TYPE=" + REIMB_TYPE + "]";
	}
	
	}
	