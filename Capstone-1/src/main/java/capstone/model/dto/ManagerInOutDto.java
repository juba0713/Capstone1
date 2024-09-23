package capstone.model.dto;

import java.util.List;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantMonthlyObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import lombok.Data;

@Data
public class ManagerInOutDto {
	
	public List<ApplicantObj> listOfApplicants;
	
	public int applicantIdPk;
	
	public String feedback;
	
	public Boolean resubmitFlg;
	
	public List<Integer> chosenApplicant;
	
	public int status;
	
	ApplicantDetailsObj applicantDetailsObj;
	
	ApplicantOfficerFeedbackObj applicantOffFeedbackObj;
	
	ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	public Boolean transferring;
	
	List<ApplicantMonthlyObj> applicantMonthlyObj;
	
	public String result;
	
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
