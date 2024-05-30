package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.EvaluatedApplicantEntity;

@Transactional
public interface EvaluatedApplicantDao extends JpaRepository<EvaluatedApplicantEntity, Integer>{

	public final String UPDATE_EVALUATED_APPLICANT = "UPDATE t_evaluated_applicant "
			+ "SET "
			+ "	token = :token "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk "
			+ "AND "
			+ "	delete_flg = false";
	
	public final String GET_EVALUATED_APPLICANT_BY_TOKEN = "SELECT e"
			+ " FROM EvaluatedApplicantEntity e"
			+ " WHERE e.token = :token"
			+ " AND e.deleteFlg = false";
		
	@Query(value=GET_EVALUATED_APPLICANT_BY_TOKEN)
	public EvaluatedApplicantEntity getEvaluatedApplicantByToken(String token) throws DataAccessException;
	
	@Modifying
	@Query(value=UPDATE_EVALUATED_APPLICANT, nativeQuery=true)
	public void updateEvaluatedApplicant(@Param("token") String token, 
			@Param("applicantIdPk") int applicantIdPk) throws DataAccessException;
}
