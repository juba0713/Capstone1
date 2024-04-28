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
			(String) objects[3],
			(Boolean) objects[4],
			(String[]) objects[5],
			(Integer) objects[6]
		);
	}
	
	public int applicantIdPk;
	
	public String email;
	
	public String projectTitle;
	
	public String description;
	
	public Boolean consent;
	
	public String[] team;
	
	public int status;
}