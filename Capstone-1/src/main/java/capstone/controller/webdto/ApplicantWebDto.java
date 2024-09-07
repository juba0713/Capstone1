package capstone.controller.webdto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ErrorObj;
import lombok.Data;

@Data
public class ApplicantWebDto {
	
	//Email
	public String email;
	
	//Agree 
	public Boolean agreeFlg;
	
	//Title
	public String projectTitle;
	
	//Description
	public String projectDescription;
	
	//Teams
	public List<String[]> teams = List.of(new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""});
	
	//Problem Statement
	public String problemStatement;
	
	//Target Market
	public String targetMarket;
	
	//Solution Description
	public String solutionDescription;
	
	//Historical Timeline
	public List<String[]> historicalTimeline = List.of(new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""}, new String[] {"",""});
	
	//Product Service Offering
	public List<String> productServiceOffering = new ArrayList<>(Arrays.asList("", "", "", "")); 
	
	//Pricing Strategy
	public List<String> pricingStrategy = new ArrayList<>(Arrays.asList("", "", "", "")); 
	
	//Intellectual Property Status
	public String intPropertyStatus;
	
	//Objectives
	public String objectives;
	
	//Scope of the proposal
	public String scopeProposal;
	
	//Methodlogy
	public String methodology;
	
//	//Curriculum Vitae
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
	public List<String> members = new ArrayList<>(Arrays.asList("", "", "", "", ""));
	
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
	
	//Feedback
	public String feedback;
	
	//Token
	public String token;
	
	//Re apply token
	public String reApplyToken;
	
	
	/*
	 * Change Password
	 */
	
	//Current Password
	public String currentPassword;
	
	//New Password
	public String newPassword;
	
	//Confirm Password
	public String confirmPassword;
	
	public ApplicantDetailsObj applicantDetailsObj;
	
	public ErrorObj error;
	
}
