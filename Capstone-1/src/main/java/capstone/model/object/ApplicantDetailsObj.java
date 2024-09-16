package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ApplicantDetailsObj {

	public int applicantIdPk;
	
	public String encryptedApplicantIdPk;
	
	public String email;
	
	public Boolean agreeFlg;
	
	public String projectTitle;
	
	public String projectDescription;
	
	public List<String[]> teams;
	
	public String problemStatement;
	
	public String targetMarket;
	
	public String solutionDescription;
	
	public List<String[]> historicalTimeline;
	
	public String[] productServiceOffering;
	
	public String[] pricingStrategy;
	
	public String intPropertyStatus;
	
	public String objectives;
	
	public String scopeProposal;
	
	public String methodology;
	
	public String vitaeFile;
	
	public String supportLink;
	
	public String groupName;
	
	public String leaderFirstName;
	
	public String leaderLastName;
	
	public String mobileNumber;
	
	public String address;
	
	public String university;
	
	public String[] members;
	
	public int technologyAns;
	
	public int productDevelopmentAns;
	
	public int competitiveLandscapeAns;
	
	public int productDesignAns;
	
	public int teamAns;
	
	public int goToMarketAns;
	
	public int manufacturingAns;
	
	public Boolean eligibilityAgreeFlg;
	
	public Boolean commitmentOneFlg;
	
	public Boolean commitmentTwoFlg;
	
	public Boolean commitmentThreeFlg;
	
	public Boolean commitmentFourFlg;
	
	public int status;
	
	public int score;
	
	public String feedback;
	
	public String certificateName;
}
