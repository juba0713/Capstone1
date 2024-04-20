package capstone.common.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity
@Configuration
public class SecurityConfig{
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private static final String USER_ACCOUNT_SQL =  "SELECT USERNAME,PASSWORD,TRUE"
														+ " FROM M_USER_INFO_ACCOUNT "
														+ " INNER JOIN M_USER_INFORMATION "
														+ " ON M_USER_INFORMATION.ID_PK = M_USER_INFO_ACCOUNT.USER_ID_PK "
														+ " WHERE USERNAME = ?" 
														+ " AND M_USER_INFORMATION.DELETE_FLG = FALSE " 
														+ " AND M_USER_INFO_ACCOUNT.DELETE_FLG = FALSE ";

	private static final String USER_ROLE_SQL = "SELECT USERNAME, ROLE FROM M_USER_INFORMATION WHERE USERNAME = ?";

	@Bean
	protected UserDetailsManager userDetailsService(){
	
	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	      
	    users.setUsersByUsernameQuery(USER_ACCOUNT_SQL);
	    users.setAuthoritiesByUsernameQuery(USER_ROLE_SQL);
	
	    return users;
	}
	
	@Bean
	protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSuccessHandler();
	}
		
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/icons/**").permitAll()	
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/fonts/**").permitAll()
				.requestMatchers("/design/**").permitAll()
				.requestMatchers("/applicant/form").permitAll()
				.requestMatchers("applicant/**").hasAnyAuthority("APPLICANT", "ADMIN")
				.requestMatchers("officer/**").hasAnyAuthority("OFFICER", "ADMIN")
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				//.defaultSuccessUrl("/applicant/home")
				.successHandler(authenticationSuccessHandler())
			)
			.logout((logout) -> logout
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.permitAll()
			);		
					
		return http.build();
	}
	
	
}
