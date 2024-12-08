package capstone.common.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.logic.UserLogic;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private UserLogic userLogic;
	
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

        String email = request.getParameter("username"); // Get the username from the login form
        System.out.println(email);
        UserInformationEntity user = userLogic.getUserByEmail(email);

        if (user != null && user.getBlockFlg()) {
            // User exists and is blocked
            if (session != null) {
                session.setAttribute("errorMessageLogin", "Your account has been blocked");
            }
        } else {
            // General invalid login attempt
            if (session != null) {
                session.setAttribute("errorMessageLogin", "Invalid username and password");
            }
        }

        super.setDefaultFailureUrl("/login"); // Set the failure URL
        super.onAuthenticationFailure(request, response, exception); // Conti
    }
}
