package capstone.model.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.AcceptedApplicantEntity;

@Transactional
public interface AcceptedApplicantDao extends JpaRepository<AcceptedApplicantEntity, Integer> {

	public final String UPDATE_PREVIOUS_ACCEPTED_APPLICANT = "UPDATE t_accepted_applicant  "
			+ "SET "
			+ "	delete_flg = true "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk ";
	
	@Modifying
	@Query(value=UPDATE_PREVIOUS_ACCEPTED_APPLICANT, nativeQuery=true)
	public void updatePreviousAcceptedApplicant(@Param("applicantIdPk") int applicantIdPk) throws DataAccessException;
	
	public final String GET_ACCEPTED_APPLICANT_BY_APPLICANT_ID_PK = "SELECT e "
			+ "FROM AcceptedApplicantEntity e "
			+ "WHERE e.applicantIdPk = :applicantIdPk "
			+ "AND e.deleteFlg = false ";
	
	@Query(value=GET_ACCEPTED_APPLICANT_BY_APPLICANT_ID_PK)
	public AcceptedApplicantEntity getAcceptedApplicantByApplicantIdPk(int applicantIdPk) throws DataAccessException;
}
