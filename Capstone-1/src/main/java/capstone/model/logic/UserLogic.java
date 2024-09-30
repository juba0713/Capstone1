package capstone.model.logic;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.ManagerDashboardEntity;
import capstone.model.dao.entity.OfficerDashboardEntity;
import capstone.model.dao.entity.TbiBoardDashboardEntity;
import capstone.model.dao.entity.UserDetailsEntity;
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
	
	/**
	 * To get user by applicant id pk
	 * @param token
	 * @return UserInformationEntity
	 */
	public List<UserInformationEntity> getUsersByApplicantIdPks(List<Integer> applicantIdPks);
	
	/**
	 * To get all users
	 * @return List<UserInformationEntity>
	 */
	public  List<UserDetailsEntity> getAllUsers();
	
	/**
	 * To delete user by changing the delete_flg to true
	 */
	public void deleteUser(int userIdPk);
	
	/**
	 * To update usesr information
	 * @param firstName
	 * @param lastName
	 * @param mobileNumber
	 * @param role
	 * @param updatedDate
	 * @param userIdPk
	 * @return int
	 */
	public int updateuser(String firstName, String lastName, String mobileNumber, String role, Timestamp updatedDate, int userIdPk);
	
	/**
	 * To get analytics details for admin dashboard
	 * @return
	 */
	public AdminDashboardEntity getDetailsForAdminDashboard();
	
	/**
	 * To get analytics details for tbiboard dashboard
	 * @return
	 */
	public TbiBoardDashboardEntity getDetailsForTbiBoardDashboard();
	
	/**
	 * To get analytics details for Officer dashboard
	 * @return
	 */
	public OfficerDashboardEntity getDetailsForOfficerDashboard();
	
	/**
	 * To get analytics details for Manager dashboard
	 * @return
	 */
	public ManagerDashboardEntity getDetailsForManagerDashboard();
}
