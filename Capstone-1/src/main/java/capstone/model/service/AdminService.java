package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.AdminInOutDto;

@Service
public interface AdminService {
	
	public AdminInOutDto getAdminDashboardDetails();
	
	public AdminInOutDto getAllUsers();
	
	public AdminInOutDto getAllApplicants();
	
	public AdminInOutDto validateInputs(AdminInOutDto inDto);
	
	public AdminInOutDto getUserDetails(AdminInOutDto inDto);
	
	public void saveUser(AdminInOutDto inDto);
	
	public void deleteUser(AdminInOutDto inDto);
	
	public AdminInOutDto updateUser(AdminInOutDto inDto);
}
