package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.RejectedApplicantEntity;

public interface RejectedApplicantDao extends JpaRepository<RejectedApplicantEntity, Integer>{

	public final String GET_REJECTED_APPLICANT_BY_TOKEN = "SELECT e"
			+ " FROM RejectedApplicantEntity e"
			+ " WHERE e.token = :token"
			+ " AND e.deleteFlg = false";
	
	@Query(value=GET_REJECTED_APPLICANT_BY_TOKEN)
	public RejectedApplicantEntity getRejectedApplicantByToken(String token) throws DataAccessException;
}
