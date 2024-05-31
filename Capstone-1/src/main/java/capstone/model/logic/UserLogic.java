package capstone.model.logic;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;

@Service
public interface UserLogic {
	// interface defines several methods related to user operations:
	// interface abstracts the operations related to user management, such as
	// retrieving and saving user information.
	/**
	 * To get user by email
	 * 
	 * @param username
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByEmail(String email);

	/**
	 * To get user by id pk
	 * 
	 * @param username
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByIdPk(int idPk);

	/**
	 * To get user account by user id pk
	 * 
	 * @param userIdPk
	 * @return UserInfoAccountEntity
	 */
	public UserInfoAccountEntity getUserAccountByUserIdPk(int userIdPk);

	/**
	 * TO save user account
	 * 
	 * @param entity
	 */
	public void saveUserAccount(UserInfoAccountEntity entity);

	/**
	 * To save user
	 * 
	 * @param entity
	 * @return int
	 */
	public int saveUser(UserInformationEntity entity);
	
	/**
	 * To get user by evaluated token
	 * @param token
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByEvaluatedToken(String token);
	
	/**
	 * To get user by rejected token
	 * @param token
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByRejectedToken(String token);
	
	/**
	 * To get user by applicant id pk
	 * @param token
	 * @return UserInformationEntity
	 */
	public UserInformationEntity getUserByApplicantIdPk(int applicantIdPk);
}
