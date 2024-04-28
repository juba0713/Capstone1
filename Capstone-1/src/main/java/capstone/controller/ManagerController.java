package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.ManagerWebDto;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.dto.OfficerInOutDto;
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
		
		return "manager/home";
	}
	
	@PostMapping("/account/activate")
	public String activateAccount(@ModelAttribute ManagerWebDto webDto) throws MessagingException{
		
		ManagerInOutDto inDto = new ManagerInOutDto();
		
		inDto.setApplicantIdPk(webDto.getApplicantIdPk());
		
		managerService.activateApplicantAccount(inDto);
		
		return "redirect:/manager/home";
	}
}