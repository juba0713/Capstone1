package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping("/login/{role}")
	public String showLoginPage(Model model, @PathVariable("role") String role) {
		
		if(!role.equals("applicant") && 
			!role.equals("officer") &&
			!role.equals("tbi-board") && 
			!role.equals("manager")) {
			
			return "redirect:/";
		}
		
		model.addAttribute("role", role);
		
		return "login";
	}
}
