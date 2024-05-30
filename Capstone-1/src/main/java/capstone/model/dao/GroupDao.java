package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.GroupEntity;

@Transactional
public interface GroupDao extends JpaRepository<GroupEntity, Integer>{
	
	public final String GET_GROUP_BY_APPLICANT_ID = "SELECT e"
			+ " FROM GroupEntity e"
			+ " WHERE e.applicantIdPk = :applicantIdPk"
			+ " AND e.deleteFlg = false";
	
	public final String UPDATE_GROUP = "UPDATE m_group "
			+ "SET"
			+ "	group_name = :groupName,"
			+ "	first_name = :firstName,"
			+ "	last_name = :lastName,"
			+ "	mobile_number = :mobileNumber,"
			+ "	address = :address,"
			+ "	university = :university "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk "
			+ "AND "
			+ "	delete_flg = false";
	
	@Modifying
	@Query(value=UPDATE_GROUP, nativeQuery=true)
	public void updateGroupByApplicantId(@Param("groupName") String groupName,
			@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			@Param("mobileNumber") String mobileNumber,
			@Param("address") String address,
			@Param("university") String university,
			@Param("applicantIdPk") int applicantIdPk
			) throws DataAccessException;
	
	@Query(value=GET_GROUP_BY_APPLICANT_ID)
	public GroupEntity getGroupByApplicantId(int applicantIdPk) throws DataAccessException;
}
