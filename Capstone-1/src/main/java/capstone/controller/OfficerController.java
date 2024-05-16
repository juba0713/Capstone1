package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.OfficerWebDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.service.OfficerService;
import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/officer")
public class OfficerController {
	
	@Autowired
	private OfficerService officerService;

	@GetMapping("/home")
	public String showOfficerHome(@ModelAttribute OfficerWebDto webDto) {
		
		OfficerInOutDto outDto = officerService.getAllApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		return "officer/home";
	}
	
	@PostMapping(value="/action", params="accept")
	public String acceptApplicant(@ModelAttribute OfficerWebDto webDto) {
		
		OfficerInOutDto inDto = new OfficerInOutDto();
		
		inDto.setApplicantIdPk(webDto.getApplicantIdPk());
		
		OfficerInOutDto outDto = officerService.acceptApplicant(inDto);	
		
		return "redirect:/officer/home";
	}
	
	@PostMapping(value="/action", params="reject")
	public String rejectApplicant(@ModelAttribute OfficerWebDto webDto) throws MessagingException {
		
		OfficerInOutDto inDto = new OfficerInOutDto();
		
		inDto.setApplicantIdPk(webDto.getApplicantIdPk());
		
		inDto.setFeedback(webDto.getFeedback());
		
		inDto.setResubmitFlg(webDto.getResubmitFlg());
		
		OfficerInOutDto outDto = officerService.rejectApplicant(inDto);
		
		return "redirect:/officer/home";
	}
}
