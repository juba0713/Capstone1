package capstone.model.dto;

import java.util.List;

import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ErrorObj;
import capstone.model.object.UserDetailsObj;
import lombok.Data;

@Data
public class AdminInOutDto {
	
	private AdminDashboardObj adminDashboardObj;

	private List<UserDetailsObj> allUsers;
	
	public List<ApplicantObj> allApplicants;
	
	public int userIdPk;
	
	private UserDetailsObj user;
	
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
	
	/*
	 * Error
	 */
	private ErrorObj errors;
	
	private String result;
}
