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
	public UserInformationEntity getUserByUsername(String username) {
		
		return userInformationDao.getUserByUsername(username);
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

}
