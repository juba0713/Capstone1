package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import capstone.controller.webdto.ManagerWebDto;
import capstone.controller.webdto.TbiBoardWebDto;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.service.ManagerService;
import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;

	@GetMapping("/home")
	public String showManagerHome(@ModelAttribute ManagerWebDto webDto) {
		
		ManagerInOutDto outDto = managerService.getAllApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		return "manager/listOfAllPassedApplicants";
	}
	
	@GetMapping("/evaluated-result")
	public String showEvaluatedApplication(@ModelAttribute ManagerWebDto webDto) {
		
		ManagerInOutDto outDto = managerService.getAllEvaluatedApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		return "manager/tbiEvalResults";
	}
	
	@GetMapping("/accepted-result")
	public String showOfficerAcceptedApplication(@ModelAttribute ManagerWebDto webDto) {
		
		ManagerInOutDto outDto = managerService.getAllAcceptedApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		return "manager/officerEvalResults";
	}
	
	@PostMapping("/account/activate")
	public String activateAccount(@ModelAttribute ManagerWebDto webDto) throws MessagingException{
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setStatus(3);
		
		inDto.setApplicantIdPk(webDto.getApplicantIdPk());
		
		managerService.activateApplicantAccount(inDto);
		
		return "redirect:/manager/home";
	}
	
	@PostMapping("/proceed")
	public String proceedApplicationToTBI(@ModelAttribute ManagerWebDto webDto) {
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		//4 - Pending for evaluation
		inDto.setStatus(4);
		
		inDto.setChosenApplicant(webDto.getChosenApplicant());
		
		managerService.updateApplicantStatus(inDto);
		
		return "redirect:/manager/home";
	}
	
	@GetMapping("/retrieve/details")
	public ResponseEntity<ManagerWebDto> getApplicantDetails(@RequestParam("applicantIdPk") String applicantIdPk) {

		
		ManagerInOutDto inDto = new ManagerInOutDto();
  
		inDto.setApplicantIdPk(Integer.parseInt(applicantIdPk));
 
		ManagerInOutDto outDto = managerService.getApplicantDetails(inDto);
  
		if(outDto.getApplicantDetailsObj() == null) {
  
		}
  
		ManagerWebDto returnWebDto = new ManagerWebDto();
		
		returnWebDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		 

		return ResponseEntity.ok(returnWebDto);
	}
	
	
}
