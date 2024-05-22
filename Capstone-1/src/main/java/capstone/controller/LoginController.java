package capstone.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
	

@Controller
public class LoginController {


	@GetMapping("/login/{role}")
	public String showLoginPage(Model model, @PathVariable("role") String role, HttpServletRequest request) {
			
		if(!role.equals("applicant") && 
			!role.equals("officer") &&
			!role.equals("tbi-board") && 
			!role.equals("manager") &&
			role.equals("")){
			
			return "redirect:/";
		}
		
		if(role.equals("applicant")) {
			request.getSession().setAttribute("fromLogin", "applicant");
		}else if(role.equals("officer")) {
			request.getSession().setAttribute("fromLogin", "officer");
		}else if(role.equals("tbi-board")) {
			request.getSession().setAttribute("fromLogin", "tbi-board");
		}else if(role.equals("manager")) {
			request.getSession().setAttribute("fromLogin", "manager");
		}
		
		model.addAttribute("role", role);
		
		return "login";
	}
	
	@PostMapping("/login/{role}")
	public String processLoginPage() {
		
		System.out.println("AW");
		
		return "login";
	}
}
