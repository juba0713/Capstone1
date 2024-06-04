package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/home")
    public String showAdminHome() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/home";
    }

    @GetMapping("/admin/applicants")
    public String showApplicants() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/applicants";
    }

    @GetMapping("/admin/users")
    public String showUsers() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/users";
    }
}
