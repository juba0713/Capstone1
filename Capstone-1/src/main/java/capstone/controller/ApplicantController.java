package capstone.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import capstone.common.constant.CommonConstant;
import capstone.controller.webdto.ApplicantWebDto;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.service.ApplicantService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantService applicantService;
	
	@Autowired
	private Environment env;

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
	public String processApplicantForm(@ModelAttribute ApplicantWebDto webDto, RedirectAttributes ra, @RequestParam("vitaeFile") MultipartFile file)  throws IOException{
		
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
		
		inDto.setVitaeFile(file);
		
		inDto.setSupportLink(webDto.getSupportLink());
		
		inDto.setGroupName(webDto.getGroupName());
		
		inDto.setGroupLeader(webDto.getGroupLeader());
		
		inDto.setLeaderNumber(webDto.getLeaderNumber());
		
		inDto.setLeaderAddress(webDto.getLeaderAddress());
		
		inDto.setMembers(webDto.getMembers());
		
		inDto.setUniversity(webDto.getUniversity());
		
		inDto.setTechnologyAns(webDto.getTechnologyAns());
		
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
		
		ApplicantInOutDto outDto = applicantService.validateApplication(inDto);
			
		if(CommonConstant.INVALID.equals(outDto.getResult())) {
			
			webDto.setError(outDto.getError());
			
			ra.addFlashAttribute("applicantWebDto", webDto);
			
			return "redirect:/applicant/form";
		}
		
		applicantService.saveApplication(inDto);
		
		ra.addFlashAttribute("success", "You have sucessfully registered! Wait for the email that will be sent to you!");
		
		return "redirect:/login";
	}
	
	/**
	 * Showing applicant home page
	 * @param webDto
	 * @return String
	 */
	@GetMapping("/home")
	public String showApplicantHome(@ModelAttribute ApplicantWebDto webDto) {
		
		ApplicantInOutDto outDto = applicantService.getApplicantDetails();
		
		webDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		
		return "applicant/home";
	}
	
	/**
	 * Change Password
	 * @param webDto
	 * @param ra
	 * @return String
	 */
	@PostMapping("/change-password")
	public String processChangePassword(@ModelAttribute ApplicantWebDto webDto, RedirectAttributes ra) {
		
		ApplicantInOutDto inDto = new ApplicantInOutDto();
		
		inDto.setCurrentPassword(webDto.getCurrentPassword());
		
		inDto.setNewPassword(webDto.getNewPassword());
		
		inDto.setConfirmPassword(webDto.getConfirmPassword());
		
		ApplicantInOutDto outDto = applicantService.validatePassword(inDto);
		
		if(CommonConstant.INVALID.equals(outDto.getResult())) {
			
			ra.addFlashAttribute("error", outDto.getError());
					
			return "redirect:/applicant/home";
		}
			
		applicantService.changePassword(inDto);
		
		return "redirect:/applicant/home";
	}
	
	/**
	 * To show the Applicant Form
	 * @return String
	 */
	@GetMapping("/form/resubmit")
	public String showResubmission(Model model, @ModelAttribute ApplicantWebDto webDto, @RequestParam("token") String token) {
		
		ApplicantInOutDto inDto = new ApplicantInOutDto();
		
		inDto.setToken(token);
		
		ApplicantInOutDto outDto = applicantService.getApplicantDetailsByToken(inDto);
			
		webDto.setEmail(outDto.getEmail());
		
		webDto.setAgreeFlg(outDto.getAgreeFlg());
		
		webDto.setProjectTitle(outDto.getProjectTitle());
		
		webDto.setProjectDescription(outDto.getProjectDescription());
		
		webDto.setTeams(outDto.getTeams());
		
		webDto.setProblemStatement(outDto.getProblemStatement());
		
		webDto.setTargetMarket(outDto.getTargetMarket());
		
		webDto.setSolutionDescription(outDto.getSolutionDescription());
		
		webDto.setHistoricalTimeline(outDto.getHistoricalTimeline());
		
		webDto.setProductServiceOffering(outDto.getProductServiceOffering());
		
		webDto.setPricingStrategy(outDto.getPricingStrategy());
		
		webDto.setIntPropertyStatus(outDto.getIntPropertyStatus());
		
		webDto.setObjectives(outDto.getObjectives());
		
		webDto.setScopeProposal(outDto.getScopeProposal());
		
		webDto.setMethodology(outDto.getMethodology());
		
		webDto.setVitaeFileName(outDto.getVitaeFileName());
		
		webDto.setSupportLink(outDto.getSupportLink());
		
		webDto.setGroupName(outDto.getGroupName());
		
		webDto.setGroupLeader(outDto.getGroupLeader());
		
		webDto.setLeaderNumber(outDto.getLeaderNumber());
		
		webDto.setLeaderAddress(outDto.getLeaderAddress());
		
		webDto.setMembers(outDto.getMembers());
		
		webDto.setUniversity(outDto.getUniversity());
		
		webDto.setTechnologyAns(outDto.getTechnologyAns());
		
		webDto.setProductDevelopmentAns(outDto.getProductDevelopmentAns());
		
		webDto.setCompetitiveLandscapeAns(outDto.getCompetitiveLandscapeAns());
		
		webDto.setProductDesignAns(outDto.getProductDesignAns());
		
		webDto.setTeamAns(outDto.getTeamAns());
		
		webDto.setGoToMarketAns(outDto.getGoToMarketAns());
		
		webDto.setManufacturingAns(outDto.getManufacturingAns());
		
		webDto.setEligibilityAgreeFlg(outDto.getEligibilityAgreeFlg());
		
		webDto.setCommitmentOneFlg(outDto.getCommitmentOneFlg());
		
		webDto.setCommitmentTwoFlg(outDto.getCommitmentTwoFlg());
		
		webDto.setCommitmentThreeFlg(outDto.getCommitmentThreeFlg());
		
		webDto.setCommitmentFourFlg(outDto.getCommitmentFourFlg());
		
		model.addAttribute("applicantWebDto", webDto);
		
		return "applicant/resubmitform";
	}
	
	/**
	 * To show the Applicant Form
	 * @return String
	 */
	@PostMapping("/form/resubmit")
	public String postResubmission(@ModelAttribute ApplicantWebDto webDto) {
		
		return "applicant/resubmitform";
	}
}
