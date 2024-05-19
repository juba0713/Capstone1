package capstone.controller.webdto;

import java.util.List;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import lombok.Data;

@Data
public class ManagerWebDto {
	
	public List<ApplicantObj> listOfApplicants;
	
	public int applicantIdPk;
	
	public List<Integer> chosenApplicant;
	
	ApplicantDetailsObj applicantDetailsObj;
}
