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

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.controller.webdto.OfficerWebDto;
import capstone.controller.webdto.TbiBoardWebDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.service.CommonService;
import capstone.model.service.TbiBoardService;

@Controller
@RequestMapping("/tbi-board")
public class TbiBoardController {
	
	@Autowired
	private TbiBoardService tbiBoardService;
	
	@Autowired
	private CommonService commonService;

	@GetMapping("/home")
	public String showTbiBoardHome(@ModelAttribute TbiBoardWebDto webDto) throws Exception {
		
		TbiBoardInOutDto outDto = tbiBoardService.getAllApplicants();
		
		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		outDto = tbiBoardService.getDetailsForTbiBoardDashboard();
		
		webDto.setTbiBoardDashboardObj(outDto.getTbiBoardDashboardObj());
		
		return "tbiboard/home";
	}
	
	@GetMapping("/evaluate")
	public String showEvaluate(@RequestParam("id") String id,@ModelAttribute TbiBoardWebDto webDto ) throws Exception{
		
		TbiBoardInOutDto inDto = new TbiBoardInOutDto();
		  
		inDto.setApplicantIdPk(Integer.parseInt(commonService.decrypt(id)));
 
		TbiBoardInOutDto outDto = tbiBoardService.getApplicantDetails(inDto);
		
		webDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		
		return "tbiboard/evaluateApplication";
	}
	
	@PostMapping("/evaluate")
	public String evaluateApplication(@ModelAttribute TbiBoardWebDto webDto, RedirectAttributes ra) throws Exception {
		
		if(CommonConstant.BLANK.equals(webDto.getTbiFeedback())) {
			
			ra.addFlashAttribute("error", MessageConstant.FEEDBACK_BLANK);
			
			ra.addFlashAttribute("tbiBoardWebDto", webDto);
			
			return "redirect:/tbi-board/evaluate?id=" + webDto.getEncryptedApplicantIdPk();
			
		}
		
		if(webDto.getCtOneRating() == 0 ||
				webDto.getCtTwoRating() == 0 ||
				webDto.getCtThreeRating() == 0 ||
				webDto.getCtFourRating() == 0 ||
				webDto.getCtFiveRating() == 0 ||
				webDto.getCtSixRating() == 0 ||
				webDto.getCtSevenRating() == 0 ||
				webDto.getCtEightRating() == 0) {
			
			ra.addFlashAttribute("error", MessageConstant.RATING_BLANK);
			
			ra.addFlashAttribute("tbiBoardWebDto", webDto);
			
			return "redirect:/tbi-board/evaluate?id=" + webDto.getEncryptedApplicantIdPk();
			
		}
		
		TbiBoardInOutDto inDto = new TbiBoardInOutDto();
		
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
		
		inDto.setTbiFeedback(webDto.getTbiFeedback());
				
		//tbiBoardService.evaluateApplicant(inDto);
		System.out.println(webDto);
		
		ra.addFlashAttribute("succMsg", "The application has been evaluated!");
		
		return "redirect:/tbi-board/home";
	}
	
	@GetMapping("/retrieve/details")
	public ResponseEntity<TbiBoardWebDto> getApplicantDetails(@RequestParam("applicantIdPk") String applicantIdPk) throws Exception {

		
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
