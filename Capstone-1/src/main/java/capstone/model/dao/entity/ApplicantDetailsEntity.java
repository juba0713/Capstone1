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
				(Integer) objects[39],
				(String) objects[40],
				(String) objects[41]				
		);	
	}
	
	public int applicantIdPk;
	
	public String email;
	
	public Boolean agreeFlg;
	
	public String projectTitle;
	
	public String projectDescription;
	
	public String[] teams;
	
	public String problemStatement;
	
	public String targetMarket;
	
	public String solutionDescription;
	
	public String[] historicalTimeline;
	
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
	
	public String memberFirstName;
	
	public String memberLastName;
	
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
