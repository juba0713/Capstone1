package capstone.model.object;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class UserDetailsObj {
	
	private int id;
	
	private String email;
	
	private String number;
	
	private String fullname;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private Timestamp createdDate;
	
	private Timestamp updatedDate;
	
	private Boolean deletable;
	
	private Boolean isApplicant;
}
