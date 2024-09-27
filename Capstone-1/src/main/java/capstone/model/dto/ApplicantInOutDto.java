package capstone.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import capstone.model.object.ErrorObj;
import lombok.Data;

@Data
public class ApplicantInOutDto {
	
	//Applicant Id Pk
	private int applicantIdPk;
	
	//Result
	private String result;
	
	//Error
	private ErrorObj error;
	
	//Email
	private String email;
	
	//Agree 
	private Boolean agreeFlg;
	
	//Title
	private String projectTitle;
	
	//Description
	private String projectDescription;
	
	//Teams
	private List<String[]> teams;
	
	//Problem Statement
	private String problemStatement;
	
	//Target Market
	private String targetMarket;
	
	//Solution Description
	private String solutionDescription;
	
	//Historical Timeline
	private List<String[]> historicalTimeline;
	
	//Product Service Offering
	private List<String> productServiceOffering;
	
	//Pricing Strategy
	private List<String> pricingStrategy;
	
	//Intellectual Property Status
	private String intPropertyStatus;
	
	//Objectives
	private String objectives;
	
	//Scope of the proposal
	private String scopeProposal;
	
	//Methodlogy
	private String methodology;
	
	//Curriculum Vitae
	private MultipartFile vitaeFile;
	private String vitaeFileName;
	
	//Support link
	private String supportLink;
	
	//Group Name
	private String groupName;
	
	//Group Leader
	private String groupLeader;
	
	//Leader Mobile Numebr
	private String leaderNumber;
	
	//Leader email address
	private String leaderAddress;
	
	//Members;
	private List<String> members;
	
	//University
	private String university;
	
	//Technology Answer
	private int technologyAns;
	
	//Product Development
	private int productDevelopmentAns;
	
	//Competitive Landscape
	private int CompetitiveLandscapeAns;
	
	//Product Development/design
	private int productDesignAns;
	
	//Team Answer
	private int teamAns;
	
	//Go-To-Market
	private int goToMarketAns;
	
	//Manufacturing 
	private int manufacturingAns;
	
	//Eligibility Agreement
	private Boolean eligibilityAgreeFlg;
	
	//Commitment One
	private Boolean commitmentOneFlg;
	
	//Commitment Two
	private Boolean commitmentTwoFlg;
	
	//Commitment Three
	private Boolean commitmentThreeFlg;
	
	//Commitment Four
	private Boolean commitmentFourFlg;
	
	/*
	 * Change Password
	 */
	
	//Current Password
	private String currentPassword;
	
	//New Password
	private String newPassword;
	
	//Confirm Password
	private String confirmPassword;
	
	/*
	 * Details
	 */
	
	ApplicantDetailsObj applicantDetailsObj;
	
	/*
	 * Resubmission
	 */
	private String token;
	
	private String feedback;
	
	private int status;
	
	private int score;
	
	private Boolean onlyOfficerFeedback;
	
	private Boolean bothFeedback;
	
	private ApplicantOfficerFeedbackObj appOffFeedbackObj;
	
	private ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	
	/*
	 * Reapply
	 */
	private String reApplyToken;
}
