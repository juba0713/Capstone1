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
			(String) objects[7]
		);
	}
	
	public int applicantIdPk;
	
	public String email;
	
	public String projectTitle;

	public int status;
	
	public String university;
	
	public int totalRating;
	
	public String acceptedBy;
	
	public String evaluatedBy;
}