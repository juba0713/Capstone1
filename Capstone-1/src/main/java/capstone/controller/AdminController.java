package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.controller.webdto.AdminWebDto;
import capstone.model.dto.AdminInOutDto;
import capstone.model.service.AdminService;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/home")
    public String showAdminHome(@ModelAttribute AdminWebDto webDto) {

        AdminInOutDto outDto = adminService.getAdminDashboardDetails();

        webDto.setAdminDashboardObj(outDto.getAdminDashboardObj());

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

    @GetMapping("/admin/users/create")
    public String showUserCreate(@ModelAttribute AdminWebDto webDto) {

        // This will return the name of the HTML file (without the .html extension)
        return "admin/createUser";
    }

    @GetMapping("/admin/users/edit-user")
    public String showEditUser() {

        return "admin/editUser";
    }

    @PostMapping("/admin/users/create")
    public String postUserCreate(@ModelAttribute AdminWebDto webDto, RedirectAttributes ra) {

        AdminInOutDto inDto = new AdminInOutDto();

        inDto.setEmail(webDto.getEmail());

        inDto.setMobileNumber(webDto.getMobileNumber());

        inDto.setFirstName(webDto.getFirstName());

        inDto.setLastName(webDto.getLastName());

        inDto.setRole(webDto.getRole());

        inDto.setPassword(webDto.getPassword());

        inDto.setConfirmPassword(webDto.getConfirmPassword());

        AdminInOutDto validate = adminService.validateInputs(inDto);

        if (CommonConstant.INVALID.equals(validate.getResult())) {

            ra.addFlashAttribute("errors", validate.getErrors());

            return "redirect:/admin/users/create";
        }

        adminService.saveUser(inDto);

        ra.addFlashAttribute("succMsg", MessageConstant.ACCOUNT_CREATED);

        // This will return the name of the HTML file (without the .html extension)
        return "redirect:/admin/users";
    }
}
