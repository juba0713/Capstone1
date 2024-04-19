package capstone.common.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, Authentication authentication)
			throws IOException, jakarta.servlet.ServletException {
		
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			// Check if the user has the "ROLE_ADMIN" authority
	        if (authorities.stream().anyMatch(role -> role.getAuthority().equals("APPLICANT"))) {
	            // Redirect to the admin page if the user has the ROLE_ADMIN
	            response.sendRedirect("/applicant/home");
	        } 
			/*
			 * else if (authorities.stream().anyMatch(role ->
			 * role.getAuthority().equals("CUSTOMER"))) { // Redirect to the default user
			 * page for default users response.sendRedirect("/home"); }
			 */
		
	}
	
}
