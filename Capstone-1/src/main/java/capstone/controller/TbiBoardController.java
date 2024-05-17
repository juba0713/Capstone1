package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.TbiBoardWebDto;
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
}
