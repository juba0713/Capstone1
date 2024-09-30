package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class AdminDashboardEntity {
	
	public AdminDashboardEntity(Object[] objects) {
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
				(Integer) objects[12],
				(Integer) objects[13],
				(Integer) objects[14]
				);
		
	}
	
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
