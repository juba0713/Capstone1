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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private static final String USER_ACCOUNT_SQL = "SELECT EMAIL,PASSWORD,TRUE"
			+ " FROM M_USER_INFO_ACCOUNT "
			+ " INNER JOIN M_USER_INFORMATION "
			+ " ON M_USER_INFORMATION.ID_PK = M_USER_INFO_ACCOUNT.USER_ID_PK "
			+ " WHERE EMAIL = ?"
			+ " AND M_USER_INFORMATION.DELETE_FLG = FALSE "
			+ " AND M_USER_INFO_ACCOUNT.DELETE_FLG = FALSE ";

	private static final String USER_ROLE_SQL = "SELECT EMAIL, ROLE FROM M_USER_INFORMATION WHERE EMAIL = ?";

	@Bean
	protected UserDetailsManager userDetailsService() {

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
	protected AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomerFailureHandler();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/test/**").permitAll()
						.requestMatchers("/login/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/js/**").permitAll()
						.requestMatchers("/favicon.ico").permitAll()
						.requestMatchers("/icons/**").permitAll()
						.requestMatchers("/images/**").permitAll()
						.requestMatchers("/fonts/**").permitAll()
						.requestMatchers("/design/**").permitAll()
						.requestMatchers("/dashboard/**").permitAll()
						.requestMatchers("/applicant/form/resubmit").permitAll()
						.requestMatchers("/applicant/form").permitAll()
						.requestMatchers("/ranking").permitAll()
						.requestMatchers("/retrieve/ranking").permitAll()
						.requestMatchers("/view/**").permitAll()
						.requestMatchers("/applicant/**").hasAnyAuthority("APPLICANT")
						.requestMatchers("/officer/**").hasAnyAuthority("OFFICER")
						.requestMatchers("/manager/**").hasAnyAuthority("MANAGER")
						.requestMatchers("/tbi-board/**").hasAnyAuthority("TBIBOARD")
						.requestMatchers("/admin/**").hasAnyAuthority("ADMIN"))
				.formLogin((form) -> form
						.loginPage("/login")
						.permitAll()
						// .failureUrl("/login")
						.failureHandler(authenticationFailureHandler())
						.usernameParameter("email")
						.passwordParameter("password")
						// .defaultSuccessUrl("/applicant/home")
						.successHandler(authenticationSuccessHandler()))
				.logout((logout) -> logout
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true)
						.permitAll());

		return http.build();
	}

}
