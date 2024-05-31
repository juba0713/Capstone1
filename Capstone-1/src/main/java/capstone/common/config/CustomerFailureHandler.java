package capstone.common.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
              
//        HttpSession session = request.getSession(false);
//        
//        String fromLogin = (String) session.getAttribute("fromLogin");
//        		
//        
//        // Fallback if no role is found (redirect to default login)
//        String redirectUrl = fromLogin != null ? "/login/" + fromLogin : "/";
//
//    
//	    if (session.getAttribute("errorMessageLogin") != null) { 
//	        String errorMessageLogin = (String) session.getAttribute("errorMessageLogin");
//	        System.out.println("NOT EMPTY");
//	        // Check if errorMessageLogin is not empty 
//	        if (!errorMessageLogin.isEmpty()) {  
//	        	System.out.println("EMPTY");
//	            session.setAttribute("errorMessageLogin", null); // Clear the message
//	        }
//	    }
//
//        
//        request.getSession().setAttribute("errorMessageLogin", "Invalid credentials for " + fromLogin);
		HttpSession session = request.getSession(false);
		session.setAttribute("errorMessageLogin", "Invalid username and password"); // Clear the message
		System.out.println("AW");
        super.setDefaultFailureUrl("/login"); // Set the failure URL
        super.onAuthenticationFailure(request, response, exception); // Continue with default behavior
    }
}
