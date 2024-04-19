package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;

@Service
public interface LoggedInUserService {

	/**
	 * Get usser information
	 * @param username
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserInformation();
	
	/**
	 * Get the user information account
	 * @param userIdPk
	 * @return
	 */
	public UserInfoAccountEntity getUserInfoAccount(int userIdPk);
}
