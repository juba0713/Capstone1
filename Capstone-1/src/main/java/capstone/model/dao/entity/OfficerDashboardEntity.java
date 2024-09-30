package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class OfficerDashboardEntity {

	public OfficerDashboardEntity(Object[] objects) {
		this(
				(Integer) objects[0],
				(Integer) objects[1],
				(Integer) objects[2],
				(Integer) objects[3],
				(Integer) objects[4]
				);
		
	}
	
	private int pendingApplicationCount;
	
	private int acceptedApplicationCount;
	
	private int rejectedApplicationEligibleCount;
	
	private int rejectedApplicationNotEligibleCount;
	
	private int resubmittedApplicationCount;
}
