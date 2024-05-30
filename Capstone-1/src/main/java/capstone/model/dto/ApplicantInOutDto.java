package capstone.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ErrorObj;
import lombok.Data;

@Data
public class ApplicantInOutDto {
	
	//Applicant Id Pk
	public int applicantIdPk;
	
	//Result
	public String result;
	
	//Error
	public ErrorObj error;
	
	//Email
	public String email;
	
	//Agree 
	public Boolean agreeFlg;
	
	//Title
	public String projectTitle;
	
	//Description
	public String projectDescription;
	
	//Teams
	public List<String[]> teams;
	
	//Problem Statement
	public String problemStatement;
	
	//Target Market
	public String targetMarket;
	
	//Solution Description
	public String solutionDescription;
	
	//Historical Timeline
	public List<String[]> historicalTimeline;
	
	//Product Service Offering
	public List<String> productServiceOffering;
	
	//Pricing Strategy
	public List<String> pricingStrategy;
	
	//Intellectual Property Status
	public String intPropertyStatus;
	
	//Objectives
	public String objectives;
	
	//Scope of the proposal
	public String scopeProposal;
	
	//Methodlogy
	public String methodology;
	
	//Curriculum Vitae
	public MultipartFile vitaeFile;
	public String vitaeFileName;
	
	//Support link
	public String supportLink;
	
	//Group Name
	public String groupName;
	
	//Group Leader
	public String groupLeader;
	
	//Leader Mobile Numebr
	public String leaderNumber;
	
	//Leader email address
	public String leaderAddress;
	
	//Members;
	public List<String> members;
	
	//University
	public String university;
	
	//Technology Answer
	public int technologyAns;
	
	//Product Development
	public int productDevelopmentAns;
	
	//Competitive Landscape
	public int CompetitiveLandscapeAns;
	
	//Product Development/design
	public int productDesignAns;
	
	//Team Answer
	public int teamAns;
	
	//Go-To-Market
	public int goToMarketAns;
	
	//Manufacturing 
	public int manufacturingAns;
	
	//Eligibility Agreement
	public Boolean eligibilityAgreeFlg;
	
	//Commitment One
	public Boolean commitmentOneFlg;
	
	//Commitment Two
	public Boolean commitmentTwoFlg;
	
	//Commitment Three
	public Boolean commitmentThreeFlg;
	
	//Commitment Four
	public Boolean commitmentFourFlg;
	
	/*
	 * Change Password
	 */
	
	//Current Password
	public String currentPassword;
	
	//New Password
	public String newPassword;
	
	//Confirm Password
	public String confirmPassword;
	
	/*
	 * Details
	 */
	
	ApplicantDetailsObj applicantDetailsObj;
	
	/*
	 * Resubmission
	 */
	public String token;
	
	public String feedback;
	
	public int status;
	
	public int score;
	
	
	/*
	 * Reapply
	 */
	public String reApplyToken;
}
