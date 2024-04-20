package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.OfficerWebDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.service.OfficerService;

@Controller
@RequestMapping("/officer")
public class OfficerController {
	
	@Autowired
	private OfficerService officerService;

	@GetMapping("/home")
	public String showOfficerHome(@ModelAttribute OfficerWebDto webDto) {
		
		OfficerInOutDto outDto = officerService.getAllApplicants();
		
		
		
		return "officer/home";
	}
}
