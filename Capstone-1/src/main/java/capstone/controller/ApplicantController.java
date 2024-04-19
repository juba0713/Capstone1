package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.ApplicantWebDto;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {

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
		
		
		return "applicant/form";
	}
}
