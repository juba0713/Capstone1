package capstone.controller.webdto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import capstone.model.dao.entity.PrescreenDetailsEntity;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import capstone.model.object.ErrorObj;
import lombok.Data;

@Data
public class ApplicantWebDto {
	
	//Email
	private String email;
	
	//Agree 
	private Boolean agreeFlg;
	
	//Title
	private String projectTitle;
	
	//Description
	private String projectDescription;
	
	//Teams
	private List<String[]> teams = List.of(new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""});
	
	//Problem Statement
	private String problemStatement;
	
	//Target Market
	private String targetMarket;
	
	//Solution Description
	private String solutionDescription;
	
	//Historical Timeline
	private List<String[]> historicalTimeline = List.of(new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""});
	
	//Product Service Offering
	private List<String> productServiceOffering = new ArrayList<>(Arrays.asList("", "", "", "")); 
	
	//Pricing Strategy
	private List<String> pricingStrategy = new ArrayList<>(Arrays.asList("", "", "", "")); 
	
	//Intellectual Property Status
	private String intPropertyStatus;
	
	//Objectives
	private String objectives;
	
	//Scope of the proposal
	private String scopeProposal;
	
	//Methodlogy
	private String methodology;
	
//	//Curriculum Vitae
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
	private List<String> members = new ArrayList<>(Arrays.asList("", "", "", "", ""));
	
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
	
	//Feedback
	private String feedback;
	
	//Token
	private String token;
	
	//Re apply token
	private String reApplyToken;
	
	private Boolean onlyOfficer;
	
	private Boolean bothFeedback;
	
	private ApplicantOfficerFeedbackObj appOffFeedbackObj;
	
	private ApplicantTbiFeedbackObj applicantTbiFeedbackObj;
	
	
	/*
	 * Change Password
	 */
	
	//Current Password
	private String currentPassword;
	
	//New Password
	private String newPassword;
	
	//Confirm Password
	private String confirmPassword;
	
	private ApplicantDetailsObj applicantDetailsObj;
	
	private ErrorObj error;
	
}
