package capstone.controller.webdto;

import java.util.List;

import capstone.model.dto.AdminInOutDto;
import capstone.model.object.ApplicantObj;
import capstone.model.object.UserDetailsObj;
import lombok.Data;

@Data
public class AdminWebDto {

	private List<UserDetailsObj> allUsers;
	
	public List<ApplicantObj> allApplicants;
}
