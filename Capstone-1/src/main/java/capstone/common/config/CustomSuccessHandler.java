package capstone.common.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.service.LoggedInUserService;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private LoggedInUserService loggedInUserService;

	@Override
	public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, Authentication authentication)
			throws IOException, jakarta.servlet.ServletException {
		
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			// Check if the user has the "ROLE_ADMIN" authority
	        if (authorities.stream().anyMatch(role -> role.getAuthority().equals("APPLICANT"))) {
	            // Redirect to the admin page if the user has the ROLE_ADMIN
	            response.sendRedirect("/applicant/home");
	        } else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("OFFICER"))) { // Redirect to the default user
				 response.sendRedirect("/officer/home"); 
			}else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("MANAGER"))) { // Redirect to the default user
				 response.sendRedirect("/manager/home"); 
			}else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("TBIBOARD"))) { // Redirect to the default user
				 response.sendRedirect("/tbi-board/home"); 
			}else if (authorities.stream().anyMatch(role -> role.getAuthority().equals("ADMIN"))) { // Redirect to the default user
				 response.sendRedirect("/admin/home"); 
			}else {
				response.sendRedirect("/login"); 
			}
	        
			 
	        
	      
	        if (authentication != null) {
	            String username = authentication.getName();
	            
	            UserInformationEntity user = loggedInUserService.getUserInformation();
	            
	            request.getSession().setAttribute("fullname", user.getFirstName() + " " + user.getLastName());
	    
	            request.getSession().setAttribute("username", username);
	      
	            request.getSession().setAttribute("id", user.getIdPk());
	            
	            request.getSession().setAttribute("initialChangePass", user.getInitialChangePass());
	        }
		
	}
	
}
