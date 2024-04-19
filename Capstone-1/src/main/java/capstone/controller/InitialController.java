package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitialController {

	@GetMapping()
	public String showInitial() {
		
		return "initial";
	}
}
