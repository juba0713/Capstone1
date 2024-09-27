package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.EvaluationDetailsEntity;

public interface EvaluationDetailsDao extends JpaRepository<EvaluationDetailsEntity, Integer> {
	
	public static final String GET_REJECTED_PRESCREEN_DETAILS_BY_TOKEN = "SELECT e "
			+ "FROM EvaluatedApplicantEntity r "
			+ "LEFT JOIN EvaluationDetailsEntity e ON e.evaluatedApplicantIdPk = r.idPk AND e.deleteFlg = false "
			+ "WHERE r.token = :token AND r.deleteFlg = false";
	
	@Query(value=GET_REJECTED_PRESCREEN_DETAILS_BY_TOKEN)
	public EvaluationDetailsEntity getEvaluationDetailsByToken(String token) throws DataAccessException;
}
