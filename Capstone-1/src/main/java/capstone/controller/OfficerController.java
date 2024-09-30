package capstone.controller;

import java.util.HashMap;

import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.controller.webdto.OfficerWebDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.service.CommonService;
import capstone.model.service.OfficerService;

@Controller
@RequestMapping("/officer")
public class OfficerController {

	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private CommonService commonService;

	@GetMapping("/home")
	public String showOfficerHome(@ModelAttribute OfficerWebDto webDto) throws Exception {

		OfficerInOutDto outDto = officerService.getAllApplicants();

		webDto.setListOfApplicants(outDto.getListOfApplicants());
		
		outDto = officerService.getDetailsForOfficerDashboard();
		
		webDto.setOfficerDashboardObj(outDto.getOfficerDashboardObj());

		return "officer/home";
	}

	@PostMapping(value = "/action", params = "accept") 
	public String acceptApplicant(@ModelAttribute OfficerWebDto webDto, RedirectAttributes ra) throws Exception {
		
		if(CommonConstant.BLANK.equals(webDto.getRecommendation())) {
			
			ra.addFlashAttribute("error", MessageConstant.FEEDBACK_BLANK);
			
			ra.addFlashAttribute("officerWebDto", webDto);
			
			return "redirect:/officer/prescreen?id=" + webDto.getEncryptedApplicantIdPk();
			
		}
				
		OfficerInOutDto inDto = new OfficerInOutDto();

		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));
		
		inDto.setCtOneFlg(webDto.getCtOneFlg());
		
		inDto.setCtOneComments(webDto.getCtOneComments());
		
		inDto.setCtTwoFlg(webDto.getCtTwoFlg());
		
		inDto.setCtTwoComments(webDto.getCtTwoComments());
		
		inDto.setCtThreeFlg(webDto.getCtThreeFlg());
		
		inDto.setCtThreeComments(webDto.getCtThreeComments());
		
		inDto.setCtFourFlg(webDto.getCtFourFlg());
		
		inDto.setCtFourComments(webDto.getCtFourComments());
		
		inDto.setCtFiveFlg(webDto.getCtFiveFlg());
		
		inDto.setCtFiveComments(webDto.getCtFiveComments());
		
		inDto.setCtSixFlg(webDto.getCtSixFlg());
		
		inDto.setCtSixComments(webDto.getCtSixComments());
		
		inDto.setCtSevenFlg(webDto.getCtSevenFlg());
		
		inDto.setCtSevenComments(webDto.getCtSevenComments());
		
		inDto.setCtEightFlg(webDto.getCtEightFlg());
		
		inDto.setCtEightComments(webDto.getCtEightComments());
		
		inDto.setCtNineFlg(webDto.getCtNineFlg());
		
		inDto.setCtNineComments(webDto.getCtNineComments());
		
		inDto.setRecommendation(webDto.getRecommendation());
		
		 officerService.acceptApplicant(inDto);
				
		return "redirect:/officer/home";
	}

	@PostMapping(value = "/action", params = "reject")
	public String rejectApplicant(@ModelAttribute OfficerWebDto webDto, RedirectAttributes ra) throws NumberFormatException, Exception {
		
		if(CommonConstant.BLANK.equals(webDto.getRecommendation())) {
			
			ra.addFlashAttribute("error", MessageConstant.FEEDBACK_BLANK);
			
			ra.addFlashAttribute("officerWebDto", webDto);
			
			return "redirect:/officer/prescreen?id=" + webDto.getEncryptedApplicantIdPk();
			
		}
		
		if(webDto.getResubmitFlg() == null) {
			
			ra.addFlashAttribute("error", MessageConstant.RESUBMIT_FLG_BLANK);
			
			ra.addFlashAttribute("officerWebDto", webDto);
			
			return "redirect:/officer/prescreen?id=" + webDto.getEncryptedApplicantIdPk();
			
		}

		OfficerInOutDto inDto = new OfficerInOutDto();

		inDto.setApplicantIdPk(Integer.valueOf(commonService.decrypt(webDto.getEncryptedApplicantIdPk())));
		
		inDto.setResubmitFlg(webDto.getResubmitFlg());
		
		inDto.setCtOneFlg(webDto.getCtOneFlg());
		
		inDto.setCtOneComments(webDto.getCtOneComments());
		
		inDto.setCtTwoFlg(webDto.getCtTwoFlg());
		
		inDto.setCtTwoComments(webDto.getCtTwoComments());
		
		inDto.setCtThreeFlg(webDto.getCtThreeFlg());
		
		inDto.setCtThreeComments(webDto.getCtThreeComments());
		
		inDto.setCtFourFlg(webDto.getCtFourFlg());
		
		inDto.setCtFourComments(webDto.getCtFourComments());
		
		inDto.setCtFiveFlg(webDto.getCtFiveFlg());
		
		inDto.setCtFiveComments(webDto.getCtFiveComments());
		
		inDto.setCtSixFlg(webDto.getCtSixFlg());
		
		inDto.setCtSixComments(webDto.getCtSixComments());
		
		inDto.setCtSevenFlg(webDto.getCtSevenFlg());
		
		inDto.setCtSevenComments(webDto.getCtSevenComments());
		
		inDto.setCtEightFlg(webDto.getCtEightFlg());
		
		inDto.setCtEightComments(webDto.getCtEightComments());
		
		inDto.setCtNineFlg(webDto.getCtNineFlg());
		
		inDto.setCtNineComments(webDto.getCtNineComments());
		
		inDto.setRecommendation(webDto.getRecommendation());
		
		 officerService.rejectApplicant(inDto);

		return "redirect:/officer/home";
	}
	
	@GetMapping(value = "/prescreen")
	public String showPrescreenList(@RequestParam("id") String id, @ModelAttribute OfficerWebDto webDto) throws Exception {
		
		OfficerInOutDto inDto = new OfficerInOutDto();
		  
		inDto.setApplicantIdPk(Integer.parseInt(commonService.decrypt(id)));
 
		OfficerInOutDto outDto = officerService.getApplicantDetails(inDto);
		
		webDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		
		webDto.setEncryptedApplicantIdPk(id);
	
		return "officer/prescreenChecklist";
	}
	
	@GetMapping("/retrieve/details")
	public ResponseEntity<OfficerWebDto> getApplicantDetails(@RequestParam("applicantIdPk") String applicantIdPk) {

		
		OfficerInOutDto inDto = new OfficerInOutDto();
  
		inDto.setApplicantIdPk(Integer.parseInt(applicantIdPk));
 
		OfficerInOutDto outDto = officerService.getApplicantDetails(inDto);
  
		if(outDto.getApplicantDetailsObj() == null) {
  
		}
  
		OfficerWebDto returnWebDto = new OfficerWebDto();
				
		returnWebDto.setApplicantDetailsObj(outDto.getApplicantDetailsObj());
		 
		return ResponseEntity.ok(returnWebDto);
	}
}
