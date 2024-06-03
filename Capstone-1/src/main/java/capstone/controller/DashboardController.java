package capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String showTest() {
        // This will return the name of the HTML file (without the .html extension)
        return "dashboard";
    }
}
