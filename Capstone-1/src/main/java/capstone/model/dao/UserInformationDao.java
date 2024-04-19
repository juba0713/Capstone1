package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.UserInformationEntity;

public interface UserInformationDao extends JpaRepository<UserInformationEntity, Integer>{

	public final String GET_USER_BY_USERNAME = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.username = :username"
			+ " AND e.deleteFlg = false";
	
	@Query(value=GET_USER_BY_USERNAME)
	public UserInformationEntity getUserByUsername(String username) throws DataAccessException;
}
