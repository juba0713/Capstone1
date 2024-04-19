package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.common.CommonConstant;
import capstone.controller.webdto.ApplicantWebDto;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.service.ApplicantService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;

	/**
	 * To show the Applicant Form
	 * @return String
	 */
	@GetMapping("/form")
	public String showApplicantForm(@ModelAttribute ApplicantWebDto webDto) {
		
		return "applicant/form";
	}
	
	/**
	 * To process the Applicant Form
	 * @return String
	 */
	@PostMapping("/form")
	public String processApplicantForm(@ModelAttribute ApplicantWebDto webDto) {
		
		ApplicantInOutDto inDto = new ApplicantInOutDto();
		
		inDto.setEmail(webDto.getEmail());
		
		inDto.setAgreeFlg(webDto.getAgreeFlg());
		
		inDto.setProjectTitle(webDto.getProjectTitle());
		
		inDto.setProjectDescription(webDto.getProjectDescription());
		
		inDto.setTeams(webDto.getTeams());
		
		inDto.setProblemStatement(webDto.getProblemStatement());
		
		inDto.setTargetMarket(webDto.getTargetMarket());
		
		inDto.setSolutionDescription(webDto.getSolutionDescription());
		
		inDto.setHistoricalTimeline(webDto.getHistoricalTimeline());
		
		inDto.setProductServiceOffering(webDto.getProductServiceOffering());
		
		inDto.setPricingStrategy(webDto.getPricingStrategy());
		
		inDto.setIntPropertyStatus(webDto.getIntPropertyStatus());
		
		inDto.setObjectives(webDto.getObjectives());
		
		inDto.setScopeProposal(webDto.getScopeProposal());
		
		inDto.setMethodology(webDto.getMethodology());
		
		inDto.setVitaeFile(webDto.getVitaeFile());
		
		inDto.setSupportLink(webDto.getSupportLink());
		
		inDto.setGroupName(webDto.getGroupName());
		
		inDto.setGroupLeader(webDto.getGroupLeader());
		
		inDto.setLeaderNumber(webDto.getLeaderNumber());
		
		inDto.setLeaderEmail(webDto.getLeaderEmail());
		
		inDto.setMemberTwo(webDto.getMemberTwo());
		
		inDto.setMemberThree(webDto.getMemberThree());
		
		inDto.setMemberFour(webDto.getMemberFour());
		
		inDto.setMemberFive(webDto.getMemberFive());
		
		inDto.setTechonologyAns(webDto.getTechonologyAns());
		
		inDto.setProductDevelopmentAns(webDto.getProductDevelopmentAns());
		
		inDto.setCompetitiveLandscapeAns(webDto.getCompetitiveLandscapeAns());
		
		inDto.setProductDesignAns(webDto.getProductDesignAns());
		
		inDto.setTeamAns(webDto.getTeamAns());
		
		inDto.setGoToMarketAns(webDto.getGoToMarketAns());
		
		inDto.setManufacturingAns(webDto.getManufacturingAns());
		
		inDto.setEligibilityAgreeFlg(webDto.getEligibilityAgreeFlg());
		
		inDto.setCommitmentOneFlg(webDto.getCommitmentOneFlg());
		
		inDto.setCommitmentTwoFlg(webDto.getCommitmentTwoFlg());
		
		inDto.setCommitmentThreeFlg(webDto.getCommitmentThreeFlg());
		
		inDto.setCommitmentFourFlg(webDto.getCommitmentFourFlg());
		
		ApplicantInOutDto outDto = applicantService.saveApplicant(inDto);
		
		if(CommonConstant.INVALID.equals(outDto.getResult())) {
			
			return "redirect:/applicant/form";
		}
		
		return "applicant/form";
	}
}
