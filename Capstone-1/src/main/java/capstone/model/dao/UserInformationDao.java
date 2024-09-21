package capstone.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.UserDetailsEntity;
import capstone.model.dao.entity.UserInformationEntity;

@Transactional
public interface UserInformationDao extends JpaRepository<UserInformationEntity, Integer>{

	public final String GET_USER_BY_USERNAME = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.email = :email"
			+ " AND e.deleteFlg = false";
	
	public final String GET_USER_BY_ID_PK = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.idPk = :idPk"
			+ " AND e.deleteFlg = false";
	
	public final String GET_USER_BY_EVALUATED_TOKEN = "SELECT u "
			+ "FROM EvaluatedApplicantEntity e "
			+ "JOIN ApplicantEntity a ON a.idPk = e.applicantIdPk "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy "
			+ "WHERE e.token = :token "
			+ "AND e.deleteFlg = false";
	
	public final String GET_USER_BY_REJECTED_TOKEN = "SELECT u "
			+ "FROM RejectedApplicantEntity e "
			+ "JOIN ApplicantEntity a ON a.idPk = e.applicantIdPk "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy "
			+ "WHERE e.token = :token "
			+ "AND e.deleteFlg = false";
	
	public final String GET_USER_BY_APPLICANT_ID_PK = "SELECT u "
			+ "FROM ApplicantEntity a "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy AND u.deleteFlg = false "
			+ "WHERE a.idPk = :applicantIdPk "
			+ "AND a.deleteFlg = false";
	
//	public final String GET_ALL_USERS = "SELECT e"
//			+ " FROM UserInformationEntity e"
//			+ " WHERE e.deleteFlg = false";
	
	public final String GET_DETAILS_FOR_ADMIN = "SELECT CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_evaluated_applicant e	\r\n"
			+ "  LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = e.id_pk AND ed.delete_flg = false\r\n"
			+ "  WHERE ed.total >= 60\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS application_passed_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_evaluated_applicant e\r\n"
			+ "  LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = e.id_pk AND ed.delete_flg = false\r\n"
			+ "  WHERE ed.total < 60\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS application_failed_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_accepted_applicant e\r\n"
			+ ") AS INTEGER) AS application_accepted_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_rejected_applicant e\r\n"
			+ "  WHERE e.resubmit_flg = false\r\n"
			+ ") AS INTEGER) AS application_rejected_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_applicant e\r\n"
			+ "  WHERE e.status IN (0)\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS in_officer_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_applicant e\r\n"
			+ "  WHERE e.status IN (4)\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS in_tbiboard_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_applicant e\r\n"
			+ "  WHERE e.status IN (3,4)\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS in_manager_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_user_information e\r\n"
			+ "  WHERE e.role = 'APPLICANT'\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS applicant_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_user_information e\r\n"
			+ "  WHERE e.role = 'OFFICER'\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS officer_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_user_information e\r\n"
			+ "  WHERE e.role = 'TBIBOARD'\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS tbiboard_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_user_information e\r\n"
			+ "  WHERE e.role = 'MANAGER'\r\n"
			+ "  AND e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS manager_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM m_user_information e\r\n"
			+ "  WHERE e.delete_flg = false\r\n"
			+ ") AS INTEGER) AS activated_account_count,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_rejected_applicant e\r\n"
			+ "  WHERE e.resubmit_flg = true\r\n"
			+ ") AS INTEGER) AS reapplication_rejected,\r\n"
			+ "CAST( (\r\n"
			+ "  SELECT COUNT(e)\r\n"
			+ "  FROM t_evaluated_applicant e\r\n"
			+ "  WHERE e.resubmit_flg = true\r\n"
			+ ") AS INTEGER) AS reapplication_failed;";
	
	public final String GET_USERS_BY_APPLICANT_ID_PKS = "SELECT u "
			+ "FROM ApplicantEntity a "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy AND u.deleteFlg = false "
			+ "WHERE a.idPk IN (:applicantIdPks) "
			+ "AND a.deleteFlg = false";
	
	public final String GET_ALL_USERS = "SELECT e.id_pk, \r\n"
			+ "e.email,\r\n"
			+ "CONCAT(e.first_name, ' ', e.last_name) AS fullName,\r\n"
			+ "e.mobile_number,\r\n"
			+ "e.role,\r\n"
			+ "e.created_date,\r\n"
			+ "e.updated_date,\r\n"
			+ "NOT ((SELECT COUNT(a.created_by)>0 \r\n"
			+ " FROM t_accepted_applicant a \r\n"
			+ " WHERE a.created_by = e.id_pk \r\n"
			+ " AND e.delete_flg = false)\r\n"
			+ " OR\r\n"
			+ " (SELECT COUNT(a.created_by)>0 \r\n"
			+ " FROM t_rejected_applicant a \r\n"
			+ " WHERE a.created_by = e.id_pk \r\n"
			+ " AND e.delete_flg = false)\r\n"
			+ " OR\r\n"
			+ " (SELECT COUNT(a.created_by)>0 \r\n"
			+ " FROM t_evaluated_applicant a 	\r\n"
			+ " WHERE a.created_by = e.id_pk \r\n"
			+ " AND e.delete_flg = false)\r\n"
			+ " OR\r\n"
			+ " (SELECT COUNT(u)>0 \r\n"
			+ "  FROM m_user_information u\r\n"
			+ "  WHERE u.role IN ('APPLICANT', 'ADMIN')\r\n"
			+ "  AND u.id_pk = e.id_pk)\r\n"
			+ " )\r\n"
			+ " AS deletable\r\n"
			+ "FROM m_user_information e\r\n"
			+ "WHERE e.delete_flg = false";
	
	public final String DELETE_USER = "UPDATE m_user_information  "
			+ "SET "
			+ "	delete_flg = true "
			+ "WHERE "
			+ "	id_pk = :userIdPk ";
	
	@Modifying
	@Query(value=DELETE_USER, nativeQuery=true)
	public void deleteUser(@Param("userIdPk") int userIdPk) throws DataAccessException;
	
	@Query(value=GET_USER_BY_APPLICANT_ID_PK)
	public UserInformationEntity getUserByApplicantIdPk(int applicantIdPk) throws DataAccessException;
	
	@Query(value=GET_USERS_BY_APPLICANT_ID_PKS)
	public List<UserInformationEntity> getUsersByApplicantIdPks(List<Integer> applicantIdPks) throws DataAccessException;

	@Query(value=GET_USER_BY_EVALUATED_TOKEN)
	public UserInformationEntity getUserByEvaluatedtoken(String token) throws DataAccessException;
	
	@Query(value=GET_USER_BY_REJECTED_TOKEN)
	public UserInformationEntity getUserByRejectedtoken(String token) throws DataAccessException;
	
	@Query(value=GET_USER_BY_USERNAME)
	public UserInformationEntity getUserByUsername(String email) throws DataAccessException;
	
	@Query(value=GET_USER_BY_ID_PK)
	public UserInformationEntity getUserByIdPk(int idPk) throws DataAccessException;
	
	@Query(value=GET_ALL_USERS, nativeQuery=true)
	public List<Object[]> getAllUsersRaw() throws DataAccessException;
	
	default List<UserDetailsEntity> getAllUsers() {
		
		List<Object[]> rawResults = getAllUsersRaw();
	    List<UserDetailsEntity> users = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	UserDetailsEntity applicant = new UserDetailsEntity(objects);  
	        users.add(applicant);
	    }

	    return users;
	};
	
	@Query(value=GET_DETAILS_FOR_ADMIN, nativeQuery=true)
	public List<Object[]> getDetailsForAdminRaw() throws DataAccessException;
	
	default AdminDashboardEntity getDetailsForAdmin() {
		
		Object[] data = getDetailsForAdminRaw().get(0);
		
		AdminDashboardEntity entity = new AdminDashboardEntity(data);
		
		return entity;
	};
}
