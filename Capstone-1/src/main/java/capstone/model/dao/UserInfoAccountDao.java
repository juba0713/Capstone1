package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.UserInfoAccountEntity;

public interface UserInfoAccountDao extends JpaRepository<UserInfoAccountEntity, Integer> {

	public final String GET_USER_INFO_ACCOUNT_BY_USER_ID_PK =  "SELECT e"
			+ " FROM UserInfoAccountEntity e"
			+ " WHERE e.userIdPk = :userIdPk"
			+ " AND e.deleteFlg = false";
	
	@Query(value=GET_USER_INFO_ACCOUNT_BY_USER_ID_PK)
	public UserInfoAccountEntity getUserInfoAccountByUserIdPk(int userIdPk) throws DataAccessException;
}
