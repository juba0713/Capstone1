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
	
	public String encryptedApplicantIdPk;

	public List<Integer> chosenApplicant;

	ApplicantDetailsObj applicantDetailsObj;
	
	ApplicantOfficerFeedbackObj applicantOffFeedbackObj;
	
	ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	List<ApplicantMonthlyObj> applicantMonthlyObj;
	
	/*
	 * Evaluation DTO
	 */
	private int ctOneRating;

	private String ctOneComments;

	private int ctTwoRating;

	private String ctTwoComments;

	private int ctThreeRating;

	private String ctThreeComments;
	
	private int ctFourRating;
	
	private String ctFourComments;
	
	private int ctFiveRating;
	
	private String ctFiveComments;
	
	private int ctSixRating;
	
	private String ctSixComments;
	
	private int ctSevenRating;
	
	private String ctSevenComments;

	private int ctEightRating;

	private String ctEightComments;

	private String managerFeedback;
}
