package capstone.model.object;

import lombok.Data;

@Data
public class AdminDashboardObj {

	private int applicationPassedCount;
	
	private int applicationFailedCount;
	
	private int applicationAcceptedCount;
	
	private int applicationRejectedCount;
	
	private int inOfficerCount;
	
	private int inTbiBoardCount;
	
	private int inManagerCount;
	
	private int applicantCount;
	
	private int officerCount;
	
	private int tbiboardCount;
	
	private int managerCount;
	
	private int activatedAccountCount;
	
	private int reapplicationRejectedCount;
	
	private int reapplicationFailedCount;
	
	private int issuedCertificateCount;
}
