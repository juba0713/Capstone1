package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tbi-board")
public class TbiBoardController {

	@GetMapping("/home")
	public String showTbiBoardHome() {
		return "tbiboard/home";
	}
}
