package capstone.controller.webdto;

import java.util.List;

import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ErrorObj;
import capstone.model.object.UserDetailsObj;
import lombok.Data;

@Data
public class AdminWebDto {

	private AdminDashboardObj adminDashboardObj;
	
	private List<UserDetailsObj> allUsers;
	
	private List<ApplicantObj> allApplicants;
	
	private int userIdPk;
	
	private UserDetailsObj user;
	
	private ErrorObj errors;
	
	
	/*
	 * Create Account
	 */
	private String email;
	
	private String mobileNumber;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private String password;
	
	private String confirmPassword;
}
