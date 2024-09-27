package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.PrescreenDetailsEntity;

public interface PrescreenDetailsDao extends JpaRepository<PrescreenDetailsEntity, Integer> {
		
	public static final String GET_REJECTED_PRESCREEN_DETAILS_BY_TOKEN = "SELECT p "
			+ "FROM RejectedApplicantEntity r "
			+ "LEFT JOIN PrescreenDetailsEntity p ON p.rejectedApplicantIdPk = r.idPk AND p.deleteFlg = false "
			+ "WHERE r.token = :token AND r.deleteFlg = false";
	
	public static final String GET_ACCEPTED_PRESCREEN_DETAILS_BY_APPLICANT_ID_PK = "SELECT p "
			+ "FROM AcceptedApplicantEntity a "
			+ "LEFT JOIN PrescreenDetailsEntity p ON p.acceptedApplicantIdPk = a.idPk AND p.deleteFlg = false "
			+ "WHERE a.applicantIdPk = :applicantIdPk AND a.deleteFlg = false";
	
	@Query(value=GET_REJECTED_PRESCREEN_DETAILS_BY_TOKEN)
	public PrescreenDetailsEntity getRejectedPrescreenDetailsByToken(String token) throws DataAccessException;
	
	@Query(value=GET_ACCEPTED_PRESCREEN_DETAILS_BY_APPLICANT_ID_PK)
	public PrescreenDetailsEntity getAcceptedPrescreenDetailsByApplicantIdPk(int applicantIdPk) throws DataAccessException;
}
