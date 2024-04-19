package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.common.constant.CommonConstant;
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
		
		System.out.println("Agree Flg: " + webDto.getAgreeFlg());
		
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
		
		inDto.setMembers(webDto.getMembers());
		
		inDto.setUniversity(webDto.getUniversity());
		
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
		
		ApplicantInOutDto outDto = applicantService.validateApplicant(inDto);
		
		if(CommonConstant.INVALID.equals(outDto.getResult())) {
			
			return "redirect:/applicant/form";
		}
		
		System.out.println(webDto.getTeams().get(0));
		
		applicantService.saveApplicant(inDto);
		
		return "applicant/form";
	}
	
	
	@GetMapping("/home")
	public String showApplicantHome() {
		
		return "applicant/home";
	}
}
