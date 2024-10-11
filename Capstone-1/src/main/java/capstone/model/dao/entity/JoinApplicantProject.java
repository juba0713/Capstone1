package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@Scope("prototype")
public class JoinApplicantProject {
	
	public JoinApplicantProject(Object[] objects) {
		this(
			(Integer) objects[0],
			(String) objects[1],
			(String) objects[2],
			(Integer) objects[3],
			(String) objects[4],
			(Integer) objects[5],
			(String) objects[6],
			(String) objects[7],
			(Boolean) objects[8]
		);
	}
	
	private int applicantIdPk;
	
	private String email;
	
	private String projectTitle;

	private int status;
	
	private String university;
	
	private int totalRating;
	
	private String acceptedBy;
	
	private String evaluatedBy;
	
	private Boolean hasResubmittedOrLowEvaluation;
}