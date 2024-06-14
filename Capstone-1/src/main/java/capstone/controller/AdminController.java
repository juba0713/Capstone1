package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import capstone.controller.webdto.AdminWebDto;
import capstone.model.dto.AdminInOutDto;
import capstone.model.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/home")
    public String showAdminHome() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/home";
    }

    @GetMapping("/admin/applicants")
    public String showApplicants(@ModelAttribute AdminWebDto webDto) {

        AdminInOutDto outDto = adminService.getAllApplicants();

        webDto.setAllApplicants(outDto.getAllApplicants());

        // This will return the name of the HTML file (without the .html extension)
        return "admin/applicants";
    }

    @GetMapping("/admin/users")
    public String showUsers(@ModelAttribute AdminWebDto webDto) {

        AdminInOutDto outDto = adminService.getAllUsers();

        webDto.setAllUsers(outDto.getAllUsers());

        // This will return the name of the HTML file (without the .html extension)
        return "admin/users";
    }

    @GetMapping("/admin/create-user")
    public String createUser(@ModelAttribute AdminWebDto webDto) {

        // This will return the name of the HTML file (without the .html extension)
        return "admin/createUser";
    }
}
