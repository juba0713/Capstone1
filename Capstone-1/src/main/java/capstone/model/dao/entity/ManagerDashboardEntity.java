package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class ManagerDashboardEntity {

	public ManagerDashboardEntity(Object[] objects) {
		this(
				(Integer) objects[0],
				(Integer) objects[1],
				(Integer) objects[2],
				(Integer) objects[3],
				(Integer) objects[4],
				(Integer) objects[5],
				(Integer) objects[6],
				(Integer) objects[7],
				(Integer) objects[8],
				(Integer) objects[9],
				(Integer) objects[10],
				(Integer) objects[11],
				(Integer) objects[12]
				);
	}
	
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
