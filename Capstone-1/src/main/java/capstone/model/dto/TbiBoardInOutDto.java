package capstone.model.dto;

import java.util.List;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.TbiBoardDashboardObj;
import lombok.Data;

@Data
public class TbiBoardInOutDto {
	
	private TbiBoardDashboardObj tbiBoardDashboardObj;

	public List<ApplicantObj> listOfApplicants;
	
	public int applicantIdPk;
	
	ApplicantDetailsObj applicantDetailsObj;
	
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

	private String tbiFeedback;
}
