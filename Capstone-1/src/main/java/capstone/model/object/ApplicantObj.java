package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ApplicantObj {
	
	//Applicant Id
	public int applicantIdPk;
	
	//Id
	public String email;
	
	public Boolean consent;
	
	public String projectTitle;
	
	public String description;
	
	public List<String> team;
	
	public int status;
	
	public String university;
}
