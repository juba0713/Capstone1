package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.AdminInOutDto;

@Service
public interface AdminService {
	
	public AdminInOutDto getAdminDashboardDetails();
	
	public AdminInOutDto getAllUsers();
	
	public AdminInOutDto getAllApplicants();
}
