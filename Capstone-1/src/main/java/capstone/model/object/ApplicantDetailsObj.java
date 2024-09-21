package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ApplicantDetailsObj {

	private int applicantIdPk;
	
	private String encryptedApplicantIdPk;
	
	private String email;
	
	private Boolean agreeFlg;
	
	private String projectTitle;
	
	private String projectDescription;
	
	private List<String[]> teams;
	
	private String problemStatement;
	
	private String targetMarket;
	
	private String solutionDescription;
	
	private List<String[]> historicalTimeline;
	
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
	
	private String[] members;
	
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
	
	private int score;
	
	private String feedback;
	
	private String certificateName;
	
	private int totalRating;
}
