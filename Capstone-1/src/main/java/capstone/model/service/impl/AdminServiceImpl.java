package capstone.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.UserInformationDao;
import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.AdminInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.UserDetailsObj;
import capstone.model.service.AdminService;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Autowired
	private UserInformationDao userDao;
	
	@Override
	public AdminInOutDto getAdminDashboardDetails() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		AdminDashboardEntity entity = userDao.getDetailsForAdmin();
		
		AdminDashboardObj obj = new AdminDashboardObj();
		
		obj.setApplicationPassedCount(entity.getApplicationPassedCount());
		
		obj.setApplicationFailedCount(entity.getApplicationFailedCount());
		
		obj.setApplicationAcceptedCount(entity.getApplicationAcceptedCount());
		
		obj.setApplicationRejectedCount(entity.getApplicationRejectedCount());
		
		obj.setInOfficerCount(entity.getInOfficerCount());
		
		obj.setInTbiBoardCount(entity.getInTbiBoardCount());
		
		obj.setInManagerCount(entity.getInManagerCount());
		
		obj.setApplicantCount(entity.getApplicantCount());
		
		obj.setOfficerCount(entity.getOfficerCount());
		
		obj.setTbiboardCount(entity.getTbiboardCount());
		
		obj.setManagerCount(entity.getManagerCount());
		
		obj.setActivatedAccountCount(entity.getActivatedAccountCount());
		
		obj.setReapplicationRejectedCount(entity.getReapplicationRejectedCount());
		
		obj.setReapplicationFailedCount(entity.getReapplicationFailedCount());
		
		outDto.setAdminDashboardObj(obj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto getAllUsers() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		List<UserInformationEntity> users = userLogic.getAllUsers();
		
		List<UserDetailsObj> usersObj = new ArrayList<>();
		
		for(UserInformationEntity user : users) {
			
			UserDetailsObj obj = new UserDetailsObj();
			
			obj.setId(user.getIdPk());
			
			obj.setEmail(user.getEmail());
			
			obj.setNumber(user.getMobileNumber());
			
			obj.setRole(user.getRole());
			
			obj.setCreatedDate(user.getCreatedDate());
			
			obj.setUpdatedDate(user.getUpdatedDate());
			
			usersObj.add(obj);
			
		}
		
		outDto.setAllUsers(usersObj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto getAllApplicants() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		List<Integer> status = List.of(0,1,2,3,4,5,6,7);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> applicantsObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
	
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
			
			obj.setScore(app.getScore());
			
			obj.setFeedback(app.getFeedback());
			
			obj.setAcceptedBy(app.getAcceptedBy());
			
			obj.setEvaluatedBy(app.getEvaluatedBy());
			
			applicantsObj.add(obj);
			
		}
		
		outDto.setAllApplicants(applicantsObj);
		
		return outDto;
	}

}
