package capstone.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.logic.UserLogic;
import capstone.model.service.LoggedInUserService;

@Service
public class LoggedInUserServiceImpl implements LoggedInUserService{
	
	@Autowired
	private UserLogic userLogic;

	@Override
	public UserInformationEntity getUserInformation() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			
			String email = authentication.getName();
			
			UserInformationEntity user = userLogic.getUserByEmail(email);
			
			return user;
		}
		
		return null;
	}

	@Override
	public UserInfoAccountEntity getUserInfoAccount(int userIdPk) {
		
		UserInformationEntity user = getUserInformation();
		
		UserInfoAccountEntity userAccount = userLogic.getUserAccountByUserIdPk(user.getIdPk());
		
		return userAccount;
	}
	
	

}
