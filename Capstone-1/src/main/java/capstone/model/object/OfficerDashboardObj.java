package capstone.model.object;

import lombok.Data;

@Data
public class OfficerDashboardObj {

	private int pendingApplicationCount;
	
	private int acceptedApplicationCount;
	
	private int rejectedApplicationEligibleCount;
	
	private int rejectedApplicationNotEligibleCount;
	
	private int resubmittedApplicationCount;
}
