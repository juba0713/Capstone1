package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class TbiBoardDashboardEntity {
	
	public TbiBoardDashboardEntity(Object[] objects) {
		this(
				(Integer) objects[0],
				(Integer) objects[1],
				(Integer) objects[2],
				(Integer) objects[3]
				);
	}
	
	private int pendingApplicantCount;
	
	private int evaluatedApplicantCount;
	
	private int acceptanceRate;
	
	private int failedRate;
}
