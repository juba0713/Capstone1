package capstone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import capstone.common.constant.CommonConstant;
import capstone.controller.webdto.ManagerWebDto;
import capstone.controller.webdto.OfficerWebDto;
import capstone.controller.webdto.TbiBoardWebDto;
import capstone.model.dto.AdminInOutDto;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.service.AdminService;
import capstone.model.service.CommonService;
import capstone.model.service.EmailService;
import capstone.model.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CommonService commonService;

	@GetMapping("/version-history1")
	public String showVersionHistoryOne(@ModelAttribute ManagerWebDto webDto) {

		// ManagerInOutDto outDto = managerService.getAllApplicants();

		// webDto.setListOfApplicants(outDto.getListOfApplicants());

		return "manager/firstApplication";
	}

	@GetMapping("/version-history")
	public String showVersionHistory(@ModelAttribute ManagerWebDto webDto) {

		// ManagerInOutDto outDto = managerService.getAllApplicants();

		// webDto.setListOfApplicants(outDto.getListOfApplicants());

		return "manager/viewHistory";
	}

	@GetMapping("/home")
	public String showManagerHome(@ModelAttribute ManagerWebDto webDto) throws Exception {

		ManagerInOutDto outDto = managerService.getAllApplicants();

		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		outDto = managerService.getDetailsForManagerDashboard();
		
		webDto.setManagerDashboardObj(outDto.getManagerDashboardObj());

		return "manager/listOfAllApplicants";
	}
	
	@GetMapping("/home/application-details")
	public String showManagerHome(@RequestParam("id") String id, @ModelAttribute ManagerWebDto webDto) throws Exception {

		if(id.isEmpty()) {
			return "redirect:/manager/home";
		}
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(id)));
		
		ManagerInOutDto outDto = managerService.getApplicantDetailsWithFeedback(inDto);
		
		webDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		
		webDto.setApplicantOffFeedbackObj(outDto.getApplicantOffFeedbackObj());
		
		webDto.setApplicantTbiFeedbackObj(outDto.getApplicantTbiFeedbackObj());

		return "manager/applicationDetails";
	}

	@GetMapping("/evaluated-result")
	public String showEvaluatedApplication(@ModelAttribute ManagerWebDto webDto) throws Exception {

		ManagerInOutDto outDto = managerService.getAllEvaluatedApplicants();

		webDto.setListOfApplicants(outDto.getListOfApplicants());

		return "manager/tbiEvalResults";
	}

	@GetMapping("/accepted-result")
	public String showOfficerAcceptedApplication(@ModelAttribute ManagerWebDto webDto) throws Exception {

		ManagerInOutDto outDto = managerService.getAllAcceptedApplicants();

		webDto.setListOfApplicants(outDto.getListOfApplicants());

		return "manager/officerEvalResults";
	}

	@GetMapping("/analytics")
	public String showAnalyticsManager(@ModelAttribute ManagerWebDto webDto) {
		AdminInOutDto outDto = adminService.getAdminDashboardDetails();

		webDto.setAdminDashboardObj(outDto.getAdminDashboardObj());
		return "manager/analytics";
	}

	@GetMapping("/rank-startups")
	public String showRankStartups(@ModelAttribute ManagerWebDto webDto) throws Exception {
		ManagerInOutDto monthlyOutDto = managerService.getAppllicantOnTodayMonth();
		
		webDto.setApplicantMonthlyObj(monthlyOutDto.getApplicantMonthlyObj());
		
		ManagerInOutDto monthlyRankingOutDto = managerService.getAppllicantRankingOnTodayMonth();
		
		webDto.setApplicantRankingMonthlyObj(monthlyRankingOutDto.getApplicantRankingMonthlyObj());
		
		return "manager/rankStartups";
	}
	
	@GetMapping("/rank-startups/evaluate")
	public String evaluateApplicantForRanking(@RequestParam("id") String id, @ModelAttribute ManagerWebDto webDto) throws Exception {
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(id)));
		
		ManagerInOutDto outDto = managerService.getApplicantDetailsWithFeedback(inDto);
		
		webDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		
		webDto.setApplicantOffFeedbackObj(outDto.getApplicantOffFeedbackObj());
		
		webDto.setApplicantTbiFeedbackObj(outDto.getApplicantTbiFeedbackObj());

		return "manager/applicationFeedbacksBoth";
	}
	
	@PostMapping("/rank-startups/evaluate")
	public String epostApplicantForRanking(@ModelAttribute ManagerWebDto webDto) throws Exception {
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));
		
		inDto.setCtOneRating(webDto.getCtOneRating());
		
		inDto.setCtOneComments(webDto.getCtOneComments());
		
		inDto.setCtTwoRating(webDto.getCtTwoRating());
		
		inDto.setCtTwoComments(webDto.getCtTwoComments());
		
		inDto.setCtThreeRating(webDto.getCtThreeRating());
		
		inDto.setCtThreeComments(webDto.getCtThreeComments());
		
		inDto.setCtFourRating(webDto.getCtFourRating());
		
		inDto.setCtFourComments(webDto.getCtFourComments());
		
		inDto.setCtFiveRating(webDto.getCtFiveRating());
		
		inDto.setCtFiveComments(webDto.getCtFiveComments());
		
		inDto.setCtSixRating(webDto.getCtSixRating());
		
		inDto.setCtSixComments(webDto.getCtSixComments());
		
		inDto.setCtSevenRating(webDto.getCtSevenRating());
		
		inDto.setCtSevenComments(webDto.getCtSevenComments());
		
		inDto.setCtEightRating(webDto.getCtEightRating());
		
		inDto.setCtEightComments(webDto.getCtEightComments());
		
		inDto.setManagerFeedback(webDto.getManagerFeedback());
		
		managerService.evaluateApplicant(inDto);

		return "redirect:/manager/rank-startups";
	}

	@PostMapping("/account/activate")
	public String activateAccount(@ModelAttribute ManagerWebDto webDto) throws NumberFormatException, Exception {

		ManagerInOutDto inDto = new ManagerInOutDto();

		inDto.setStatus(3);

		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));

		managerService.activateApplicantAccount(inDto);

		return "redirect:/manager/accepted-result";
	}

	@PostMapping("/proceed")
	public String proceedApplicationToTBI(@ModelAttribute ManagerWebDto webDto, RedirectAttributes ra)
			throws NumberFormatException, Exception {

		ManagerInOutDto inDto = new ManagerInOutDto();

		if (webDto.getChosenApplicant() == null) {

			ra.addFlashAttribute("errorMsg", "Please select at least one application to send to the TbiBoard!");

			return "redirect:/manager/accepted-result";
		}

		// 4 - Pending for evaluation
		inDto.setStatus(4);
		
		List<Integer> decryptedId = new ArrayList<>();
		
		for(String id : webDto.getChosenApplicant()) {
			decryptedId.add(Integer.valueOf(commonService.decrypt(id)));
		}
		
		inDto.setChosenApplicant(decryptedId);

		inDto.setTransferring(true);
				
		managerService.updateApplicantStatus(inDto);

		ra.addFlashAttribute("succMsg", "The application/s has been sent to the TbiBoard!");

		return "redirect:/manager/accepted-result";
	}

	@GetMapping("/retrieve/details")
	public ResponseEntity<ManagerWebDto> getApplicantDetails(@RequestParam("applicantIdPk") String applicantIdPk) {

		ManagerInOutDto inDto = new ManagerInOutDto();

		System.out.println("ID: " + applicantIdPk);

		inDto.setApplicantIdPk(Integer.parseInt(applicantIdPk));

		ManagerInOutDto outDto = managerService.getApplicantDetails(inDto);

		if (outDto.getApplicantDetailsObj() == null) {

		}

		ManagerWebDto returnWebDto = new ManagerWebDto();

		returnWebDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());

		return ResponseEntity.ok(returnWebDto);
	}

	@PostMapping(value = "/qualified", params = "yes")
	public String qualifiedResubmissionYes(@ModelAttribute ManagerWebDto webDto) throws NumberFormatException, Exception {

		ManagerInOutDto inDto = new ManagerInOutDto();

		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));

		inDto.setStatus(6);

		managerService.sendResubmissionMail(inDto);

		return "redirect:/manager/evaluated-result";
	}

	@PostMapping(value = "/qualified", params = "no")
	public String qualifiedResubmissionNo(@ModelAttribute ManagerWebDto webDto) throws NumberFormatException, Exception {

		ManagerInOutDto inDto = new ManagerInOutDto();

		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));

		inDto.setStatus(7);

		managerService.sendResubmissionMail(inDto);

		return "redirect:/manager/evaluated-result";
	}
	
	@GetMapping("/evaluate")
	public String showEvaluationForManager(@ModelAttribute ManagerWebDto webDto) throws Exception {
		
		
		
		return "manager/applicationFeedbacksBoth";
	}
	


	@PostMapping("/issue/certificate")
	public String issuedCertificate(@ModelAttribute ManagerWebDto webDto, RedirectAttributes ra) throws NumberFormatException, Exception {
		
		System.out.println(webDto.getEncryptedApplicantIdPk());
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));
		
		ManagerInOutDto outDto = managerService.issuedCertificate(inDto);
		
		if(CommonConstant.INVALID.equals(outDto.getResult())) {
			ra.addFlashAttribute("errorMsg", "There's a problem issuing a certificate!");
			return "redirect:/manager/evaluated-result";
		}
		ra.addFlashAttribute("successMsg", "A certificate has been issued");
		return "redirect:/manager/evaluated-result";
	}
	
	
}
