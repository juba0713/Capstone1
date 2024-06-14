package capstone.model.dto;

import java.util.List;

import capstone.model.object.ApplicantObj;
import capstone.model.object.UserDetailsObj;
import lombok.Data;

@Data
public class AdminInOutDto {

	private List<UserDetailsObj> allUsers;
	
	public List<ApplicantObj> allApplicants;
}
