package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ApplicantObj {
	
	//Applicant Id
	public int applicantIdPk;
	
	//Id
	public String email;
		
	public String projectTitle;
	
	public int status;
	
	public String university;
	
	public int score;
	
	public String feedback;
}
