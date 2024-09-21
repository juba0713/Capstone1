package capstone.model.object;

import lombok.Data;

@Data
public class ApplicantOfficerFeedbackObj {
	private Boolean ctOneFlg;
	
	private String ctOneComments;
	
	private Boolean ctTwoFlg;
	
	private String ctTwoComments;

	private Boolean ctThreeFlg;
	
	private String ctThreeComments;

	private Boolean ctFourFlg;
	
	private String ctFourComments;

	private Boolean ctFiveFlg;

	private String ctFiveComments;

	private Boolean ctSixFlg;

	private String ctSixComments;

	private Boolean ctSevenFlg;

	private String ctSevenComments;

	private Boolean ctEightFlg;

	private String ctEightComments;

	private Boolean ctNineFlg;

	private String ctNineComments;

	private String recommendation;
}
