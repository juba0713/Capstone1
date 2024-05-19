package capstone.model.object;

import java.util.List;

import lombok.Data;

@Data
public class ErrorObj {
	
	public List<String> emailError;
	
	public List<String> projectTitleError;
	
	public List<String> projectDescriptionError;
	
	public List<String> teamsError;
	
	public List<String> problemStatementError;
	
	public List<String> targetMarketError;
	
	public List<String> solutionDescriptionError;
	
	public List<String> historicalTimelineError;
	
	public List<String> productServiceOfferingError;
	
	public List<String> pricingStrategyError;
	
	public List<String> objectivesError;
	
	public List<String> scopeProposalError;
	
	public List<String> methodologyError;
	
	public List<String> groupNameError;
	
	public List<String> groupLeaderError;
	
	public List<String> leaderNumberError;
	
	public List<String> leaderEmailError;
	
	public String agreeFlgError;
	
	public String technologyAnsError;

	public String productDevelopmentAnsError;

	public String CompetitiveLandscapeAnsError;

	public String productDesignAnsError;

	public String teamAnsError;

	public String goToMarketAnsError;

	public String manufacturingAnsError;

	public String eligibilityAgreeFlgError;

	public String commitmentOneFlgError;

	public String commitmentTwoFlgError;

	public String commitmentThreeFlgError;

	public String commitmentFourFlgError;
		
	public List<String> newPasswordError;

	public List<String> currentPasswordError;
	
	public List<String> confirmPasswordError;
}
