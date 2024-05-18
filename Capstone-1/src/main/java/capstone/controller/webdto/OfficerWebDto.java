package capstone.controller.webdto;

import java.util.List;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import lombok.Data;

@Data
public class OfficerWebDto {

	public List<ApplicantObj> listOfApplicants;
	
	public int applicantIdPk;
	
	public String feedback;
	
	public Boolean resubmitFlg;
	
	ApplicantDetailsObj applicantDetailsObj;
	
	public String email;
}
