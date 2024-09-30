package capstone.model.object;

import lombok.Data;

@Data
public class TbiBoardDashboardObj {

	private int pendingApplicantCount;
	
	private int evaluatedApplicantCount;
	
	private int acceptanceRate;
	
	private int failedRate;
}
