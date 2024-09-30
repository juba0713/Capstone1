package capstone.model.object;

import lombok.Data;

@Data
public class ManagerDashboardObj {

	private int totalApplicationsCount;
	
	private int inOfficerCount;
	
	private int inTbiboardCount;
	
	private int issuedCeritifcatesCount;
	
	private int passedApplicationsCount;
	
	private int failedApplicationsCount;
	
	private int acceptedApplicationsCount;
	
	private int evalutedApplicaitonsCount;
	
	private int acceptanceRate;
	
	private int rejectionRate;
	
	private int resubmittedApplicationsCount;
	
	private int rejectedApplicationEligibleCount;
	
	private int rejectedApplicationNotEligibleCount;
}
