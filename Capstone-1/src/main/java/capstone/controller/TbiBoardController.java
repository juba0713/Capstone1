package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import capstone.controller.webdto.OfficerWebDto;
import capstone.controller.webdto.TbiBoardWebDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.service.TbiBoardService;

@Controller
@RequestMapping("/tbi-board")
public class TbiBoardController {
	
	@Autowired
	private TbiBoardService tbiBoardService;

	@GetMapping("/home")
	public String showTbiBoardHome(@ModelAttribute TbiBoardWebDto webDto) {
		
		TbiBoardInOutDto outDto = tbiBoardService.getAllApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		return "tbiboard/home";
	}
	
	@PostMapping("/evaluate")
	public String evaluateApplication(@ModelAttribute TbiBoardWebDto webDto, RedirectAttributes ra) {
		
		TbiBoardInOutDto inDto = new TbiBoardInOutDto();
		
		inDto.setApplicantIdPk(webDto.getApplicantIdPk());
		
		inDto.setScore(webDto.getScore());
		
		inDto.setFeedback(webDto.getFeedback());
		
		tbiBoardService.evaluateApplicant(inDto);
		
		ra.addFlashAttribute("succMsg", "The application has been evaluated!");
		
		return "redirect:/tbi-board/home";
	}
	
	@GetMapping("/retrieve/details")
	public ResponseEntity<TbiBoardWebDto> getApplicantDetails(@RequestParam("applicantIdPk") String applicantIdPk) {

		
		TbiBoardInOutDto inDto = new TbiBoardInOutDto();
  
		inDto.setApplicantIdPk(Integer.parseInt(applicantIdPk));
 
		TbiBoardInOutDto outDto = tbiBoardService.getApplicantDetails(inDto);
  
		if(outDto.getApplicantDetailsObj() == null) {
  
		}
  
		TbiBoardWebDto returnWebDto = new TbiBoardWebDto();
		
		returnWebDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		 

		return ResponseEntity.ok(returnWebDto);
	}
}
