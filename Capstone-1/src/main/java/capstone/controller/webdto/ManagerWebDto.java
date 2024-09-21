package capstone.controller.webdto;

import java.util.List;

import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantMonthlyObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import lombok.Data;

@Data
public class ManagerWebDto {
	private AdminDashboardObj adminDashboardObj;

	public List<ApplicantObj> listOfApplicants;

	public int applicantIdPk;

	public List<Integer> chosenApplicant;

	ApplicantDetailsObj applicantDetailsObj;
	
	ApplicantOfficerFeedbackObj applicantOffFeedbackObj;
	
	ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	List<ApplicantMonthlyObj> applicantMonthlyObj;
}
