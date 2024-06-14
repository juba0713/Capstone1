package capstone.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.AdminInOutDto;
import capstone.model.logic.UserLogic;
import capstone.model.object.UserDetailsObj;
import capstone.model.service.AdminService;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private UserLogic userLogic;

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

}
