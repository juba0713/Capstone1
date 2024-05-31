package capstone.model.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.UserInfoAccountDao;
import capstone.model.dao.UserInformationDao;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
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

}
