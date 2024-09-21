package capstone.model.dao.entity;


import org.springframework.context.annotation.Scope;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Scope("prototype")
public class ApplicantDetailsEntity {
	
	public ApplicantDetailsEntity(Object[] objects) {
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
				(String) objects[39],			
				(Integer) objects[40]
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
	
	private int totalRating;
}
