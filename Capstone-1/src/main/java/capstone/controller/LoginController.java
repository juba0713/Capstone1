package capstone.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import capstone.controller.webdto.ManagerWebDto;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
	

@Controller
public class LoginController {
	
	@Autowired
	ManagerService managerService;


//	@GetMapping("/login/{role}")
//	public String showLoginPage(Model model, @PathVariable("role") String role, HttpServletRequest request) {
//			
//		if(!role.equals("applicant") && 
//			!role.equals("officer") &&
//			!role.equals("tbi-board") && 
//			!role.equals("manager") &&
//			role.equals("")){
//			
//			return "redirect:/";
//		}
//		
//		
//		String error = (String)request.getSession().getAttribute("errorMessageLogin");
//		
//		if(error != null) {
//			model.addAttribute("errorMessageLogin", error);
//			request.getSession().setAttribute("errorMessageLogin", null);
//		}
//		
//		if(role.equals("applicant")) {
//			request.getSession().setAttribute("fromLogin", "applicant");
//		}else if(role.equals("officer")) {
//			request.getSession().setAttribute("fromLogin", "officer");
//		}else if(role.equals("tbi-board")) {
//			request.getSession().setAttribute("fromLogin", "tbi-board");
//		}else if(role.equals("manager")) {
//			request.getSession().setAttribute("fromLogin", "manager");
//		}
//		
//		model.addAttribute("role", role);
//		
//		return "login";
//	}
	
	@GetMapping("/login")
	public String showLoginPage(Model model, HttpServletRequest request) {
		String error = (String)request.getSession().getAttribute("errorMessageLogin");
		
		if(error != null) {
			model.addAttribute("errorMessageLogin", error);
			request.getSession().setAttribute("errorMessageLogin", null);
		}
		
		return "login";
	}
	
	@GetMapping("/ranking")
	public String showRanking(@ModelAttribute ManagerWebDto webDto) {
		
		return "rankingPublic";
	}
	
	@GetMapping("/retrieve/ranking")
	public ResponseEntity<ManagerWebDto> getRankingByYearMonth(@RequestParam("month") int month,
			@RequestParam("year") int year) throws Exception {

		ManagerInOutDto inDto = new ManagerInOutDto();
  
		inDto.setMonth(month);
		
		inDto.setYear(year);
		
		ManagerInOutDto outDto = managerService.getAppllicantRankingByYearMonth(inDto);
		
		ManagerWebDto returnWebDto = new ManagerWebDto();
				
		returnWebDto.setApplicantRankingMonthlyObj(outDto.getApplicantRankingMonthlyObj());
		 
		return ResponseEntity.ok(returnWebDto);
	}
	
}
