package capstone.model.dao.entity;

import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class ApplicantDetailsFeedbackEntity {
	public ApplicantDetailsFeedbackEntity(Object[] objects) {
	    this(
	        (Integer) objects[0],
	        (String) objects[1],
	        (Boolean) objects[2],
	        (String) objects[3],
	        (String) objects[4],
	        (String[]) objects[5],
	        (String) objects[6],
	        (String) objects[7],
	        (String) objects[8],
	        (String[]) objects[9],
	        (String[]) objects[10],
	        (String[]) objects[11],
	        (String) objects[12],
	        (String) objects[13],
	        (String) objects[14],
	        (String) objects[15],
	        (String) objects[16],
	        (String) objects[17],
	        (String) objects[18],
	        (String) objects[19],
	        (String) objects[20],
	        (String) objects[21],
	        (String) objects[22],
	        (String) objects[23],
	        (String) objects[24],
	        (String) objects[25],
	        (Integer) objects[26],
	        (Integer) objects[27],
	        (Integer) objects[28],
	        (Integer) objects[29],
	        (Integer) objects[30],
	        (Integer) objects[31],
	        (Integer) objects[32],
	        (Boolean) objects[33],
	        (Boolean) objects[34],
	        (Boolean) objects[35],
	        (Boolean) objects[36],
	        (Boolean) objects[37],
	        (Integer) objects[38],
	        (String) objects[39], //asd
	        (Boolean) objects[40],
	        (String) objects[41],
	        (Boolean) objects[42],
	        (String) objects[43],
	        (Boolean) objects[44],
	        (String) objects[45],
	        (Boolean) objects[46],
	        (String) objects[47],
	        (Boolean) objects[48],
	        (String) objects[49],
	        (Boolean) objects[50],
	        (String) objects[51],
	        (Boolean) objects[52],
	        (String) objects[53],
	        (Boolean) objects[54],
	        (String) objects[55],
	        (Boolean) objects[56],
	        (String) objects[57],
	        (String) objects[58], //end of officer
	        (Integer) objects[59],
	        (String) objects[60],
	        (Integer) objects[61],
	        (String) objects[62],
	        (Integer) objects[63],
	        (String) objects[64],
	        (Integer) objects[65],
	        (String) objects[66],
	        (Integer) objects[67],
	        (String) objects[68],
	        (Integer) objects[69],
	        (String) objects[70],
	        (Integer) objects[71],
	        (String) objects[72],
	        (Integer) objects[73],
	        (String) objects[74],
	        (String) objects[75] 
	    );
	}


	
	private int applicantIdPk;
	
	private String email;
	
	private Boolean agreeFlg;
	
	private String projectTitle;
	
	private String projectDescription;
	
	private String[] teams;
	
	private String problemStatement;
	
	private String targetMarket;
	
	private String solutionDescription;
	
	private String[] historicalTimeline;
	
	private String[] productServiceOffering;
	
	private String[] pricingStrategy;
	
	private String intPropertyStatus;
	
	private String objectives;
	
	private String scopeProposal;
	
	private String methodology;
	
	private String vitaeFile;
	
	private String supportLink;
	
	private String groupName;
	
	private String leaderFirstName;
	
	private String leaderLastName;
	
	private String mobileNumber;
	
	private String address;
	
	private String university;
	
	private String memberFirstName;
	
	private String memberLastName;
	
	private int technologyAns;
	
	private int productDevelopmentAns;
	
	private int competitiveLandscapeAns;
	
	private int productDesignAns;
	
	private int teamAns;
	
	private int goToMarketAns;
	
	private int manufacturingAns;
	
	private Boolean eligibilityAgreeFlg;
	
	private Boolean commitmentOneFlg;
	
	private Boolean commitmentTwoFlg;
	
	private Boolean commitmentThreeFlg;
	
	private Boolean commitmentFourFlg;
	
	private int status;
	
	private String certificateName;
	
	private Boolean oCtOneFlg;
	
	private String oCtOneComments;
	
	private Boolean oCtTwoFlg;
	
	private String oCtTwoComments;

	private Boolean oCtThreeFlg;
	
	private String oCtThreeComments;

	private Boolean oCtFourFlg;
	
	private String oCtFourComments;

	private Boolean oCtFiveFlg;

	private String oCtFiveComments;

	private Boolean oCtSixFlg;

	private String oCtSixComments;

	private Boolean oCtSevenFlg;

	private String oCtSevenComments;

	private Boolean oCtEightFlg;

	private String oCtEightComments;

	private Boolean oCtNineFlg;

	private String oCtNineComments;

	private String recommendation;
	
	private int tCtOneRating;

	private String tCtOneComments;

	private int tCtTwoRating;

	private String tCtTwoComments;

	private int tCtThreeRating;

	private String tCtThreeComments;
	
	private int tCtFourRating;
	
	private String tCtFourComments;
	
	private int tCtFiveRating;
	
	private String tCtFiveComments;
	
	private int tCtSixRating;
	
	private String tCtSixComments;
	
	private int tCtSevenRating;
	
	private String tCtSevenComments;

	private int tCtEightRating;

	private String tCtEightComments;

	private String tbiFeedback;
}
