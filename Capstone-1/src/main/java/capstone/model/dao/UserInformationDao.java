package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.UserInformationEntity;

public interface UserInformationDao extends JpaRepository<UserInformationEntity, Integer>{

	public final String GET_USER_BY_USERNAME = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.email = :email"
			+ " AND e.deleteFlg = false";
	
	public final String GET_USER_BY_ID_PK = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.idPk = :idPk"
			+ " AND e.deleteFlg = false";
	
	@Query(value=GET_USER_BY_USERNAME)
	public UserInformationEntity getUserByUsername(String email) throws DataAccessException;
	
	@Query(value=GET_USER_BY_ID_PK)
	public UserInformationEntity getUserByIdPk(int idPk) throws DataAccessException;
}
