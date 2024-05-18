package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ApplicantDetailsObj {

	public int applicantIdPk;
	
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
	
	public String methodology;
	
	public String vitaeFile;
	
	public String supportLink;
	
	public String groupName;
	
	public String leaderFirstName;
	
	public String leaderLastName;
	
	public String mobileNumber;
	
	public String emailAddress;
	
	public String university;
	
	public String[] members;
}
