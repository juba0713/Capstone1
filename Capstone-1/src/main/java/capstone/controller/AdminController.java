package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/home")
    public String showTest() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/home";
    }

    @GetMapping("/admin/users")
    public String showUserTest() {
        // This will return the name of the HTML file (without the .html extension)
        return "admin/users";
    }
}
