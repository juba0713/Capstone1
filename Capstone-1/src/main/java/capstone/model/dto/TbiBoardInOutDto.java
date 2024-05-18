package capstone.model.dto;

import java.util.List;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import lombok.Data;

@Data
public class TbiBoardInOutDto {

	public List<ApplicantObj> listOfApplicants;
	
	public int score;
	
	public String feedback;
	
	public int applicantIdPk;
	
	ApplicantDetailsObj applicantDetailsObj;
}
