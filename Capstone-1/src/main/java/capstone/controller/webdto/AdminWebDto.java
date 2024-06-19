package capstone.controller.webdto;

import java.util.List;

import capstone.model.dto.AdminInOutDto;
import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.UserDetailsObj;
import lombok.Data;

@Data
public class AdminWebDto {

	private AdminDashboardObj adminDashboardObj;
	
	private List<UserDetailsObj> allUsers;
	
	private List<ApplicantObj> allApplicants;
}
