package capstone.model.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.UserInfoAccountDao;
import capstone.model.dao.UserInformationDao;
import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.ManagerDashboardEntity;
import capstone.model.dao.entity.OfficerDashboardEntity;
import capstone.model.dao.entity.TbiBoardDashboardEntity;
import capstone.model.dao.entity.UserDetailsEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import java.sql.Timestamp;
import capstone.model.logic.UserLogic;

@Service
public class UserLogicImpl implements UserLogic {
	
	@Autowired
	private UserInformationDao userInformationDao;
	
	@Autowired
	private UserInfoAccountDao userInfoAccountDao;

	@Override
	public UserInformationEntity getUserByEmail(String email) {
		
		return userInformationDao.getUserByUsername(email);
	}

	@Override
	public UserInfoAccountEntity getUserAccountByUserIdPk(int userIdPk) {
		
		return userInfoAccountDao.getUserInfoAccountByUserIdPk(userIdPk);
	}

	@Override
	public void saveUserAccount(UserInfoAccountEntity entity) {
		
		userInfoAccountDao.save(entity);
	}

	@Override
	public int saveUser(UserInformationEntity entity) {
		
		userInformationDao.save(entity);
		
		return entity.getIdPk();
	}

	@Override
	public UserInformationEntity getUserByIdPk(int idPk) {

		return userInformationDao.getUserByIdPk(idPk);
	}

	@Override
	public UserInformationEntity getUserByEvaluatedToken(String token) {
		
		return userInformationDao.getUserByEvaluatedtoken(token);
	}

	@Override
	public UserInformationEntity getUserByRejectedToken(String token) {
		
		return userInformationDao.getUserByRejectedtoken(token);
	}

	@Override
	public UserInformationEntity getUserByApplicantIdPk(int applicantIdPk) {

		return userInformationDao.getUserByApplicantIdPk(applicantIdPk);
	}

	@Override
	public  List<UserDetailsEntity> getAllUsers() {
		
		return userInformationDao.getAllUsers();
	}

	@Override
	public List<UserInformationEntity> getUsersByApplicantIdPks(List<Integer> applicantIdPks) {
		
		return userInformationDao.getUsersByApplicantIdPks(applicantIdPks);
	}

	@Override
	public void deleteUser(int userIdPk) {
		
		userInformationDao.deleteUser(userIdPk);
		
	}

	@Override
	public int updateuser(String firstName, String lastName, String mobileNumber, String role,
			Timestamp updatedDate, int userIdPk) {
		
		return userInformationDao.updateUser(firstName, lastName, mobileNumber, role, updatedDate, userIdPk);
	}

	@Override
	public AdminDashboardEntity getDetailsForAdminDashboard() {

		return userInformationDao.getDetailsForAdmiDashboard();
	}

	@Override
	public TbiBoardDashboardEntity getDetailsForTbiBoardDashboard() {
	
		return userInformationDao.getDetailsForTbiBoardDashboard();
	}

	@Override
	public OfficerDashboardEntity getDetailsForOfficerDashboard() {
		
		return userInformationDao.getDetailsForOfficerDashboard();
	}

	@Override
	public ManagerDashboardEntity getDetailsForManagerDashboard() {

		return userInformationDao.getDetailsForManagerDashboard();
	}



}
