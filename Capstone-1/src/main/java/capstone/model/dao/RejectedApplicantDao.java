package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import capstone.model.dao.entity.RejectedApplicantEntity;

public interface RejectedApplicantDao extends JpaRepository<RejectedApplicantEntity, Integer>{

	public final String GET_REJECTED_APPLICANT_BY_TOKEN = "SELECT e"
			+ " FROM RejectedApplicantEntity e"
			+ " WHERE e.token = :token"
			+ " AND e.deleteFlg = false";
	
	public final String UPDATE_PREVIOUS_REJECTED_APPLICANT = "UPDATE t_rejected_applicant  "
			+ "SET "
			+ "	delete_flg = true"
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk ";
		
	@Query(value=GET_REJECTED_APPLICANT_BY_TOKEN)
	public RejectedApplicantEntity getRejectedApplicantByToken(String token) throws DataAccessException;
	
	
	@Modifying
	@Query(value=UPDATE_PREVIOUS_REJECTED_APPLICANT, nativeQuery=true)
	public void updatePreviousRejectedApplicant(@Param("applicantIdPk") int applicantIdPk) throws DataAccessException;
}
