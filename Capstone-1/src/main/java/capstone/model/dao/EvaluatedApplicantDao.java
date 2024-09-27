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
			+ "	token = :token, "
			+ "	resubmit_flg = :resubmitFlg "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk "
			+ "AND "
			+ "	delete_flg = false";
	
	public final String UPDATE_PREVIOUS_EVALUATED_APPLICANT = "UPDATE t_evaluated_applicant "
			+ "SET "
			+ "	delete_flg = true "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk "
			+ "AND applicant_id_pk < ("
			+ "    SELECT MAX(applicant_id_pk) "
			+ "    FROM t_evaluated_applicant "
			+ "    WHERE applicant_id_pk = :applicantIdPk "
			+ "    AND delete_flg = false "
			+ ");";
	
	public final String GET_EVALUATED_APPLICANT_BY_TOKEN = "SELECT e"
			+ " FROM EvaluatedApplicantEntity e"
			+ " WHERE e.token = :token"
			+ " AND e.deleteFlg = false"
			+ " AND e.idPk = (SELECT MAX(e2.idPk) FROM EvaluatedApplicantEntity e2 WHERE e2.token = :token)";
	
	public final String GET_EVALUATED_APPLICANT_BY_ID= "SELECT e"
			+ " FROM EvaluatedApplicantEntity e"
			+ " WHERE e.deleteFlg = false"
			+ " AND e.idPk = :idPk";
		
	@Query(value=GET_EVALUATED_APPLICANT_BY_TOKEN)
	public EvaluatedApplicantEntity getEvaluatedApplicantByToken(String token) throws DataAccessException;
	
	@Modifying
	@Query(value=UPDATE_EVALUATED_APPLICANT, nativeQuery=true)
	public void updateEvaluatedApplicant(@Param("token") String token, 
			@Param("resubmitFlg") boolean resubmitFlg,
			@Param("applicantIdPk") int applicantIdPk) throws DataAccessException;
	
	@Modifying
	@Query(value=UPDATE_PREVIOUS_EVALUATED_APPLICANT, nativeQuery=true)
	public void updatePreviousEvaluatedApplicant(@Param("applicantIdPk") int applicantIdPk) throws DataAccessException;
	
	@Query(value=GET_EVALUATED_APPLICANT_BY_ID)
	public EvaluatedApplicantEntity getEvaluatedApplicantById(int idPk) throws DataAccessException;
	
	
}
