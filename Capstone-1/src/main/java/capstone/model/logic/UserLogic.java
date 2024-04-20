package capstone.model.logic;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;

@Service
public interface UserLogic {

	/**
	 * To get user by username
	 * @param username
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByUsername(String username);
	
	/**
	 * To get user account by user id pk
	 * @param userIdPk
	 * @return UserInfoAccountEntity
	 */
	public UserInfoAccountEntity getUserAccountByUserIdPk(int userIdPk);
	
	/**
	 * TO save user account
	 * @param entity
	 */
	public void saveUserAccount(UserInfoAccountEntity entity);
	
	/**
	 * To save user
	 * @param entity
	 * @return int
	 */
	public int saveUser(UserInformationEntity entity);
}
