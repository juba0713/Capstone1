package capstone.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import jakarta.transaction.Transactional;

public interface ApplicantDao extends JpaRepository<ApplicantEntity, Integer>{
	
	public final String GET_ALL_APPLICANTS_BY_STATUS = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	p.project_title,"
			+ "	p.project_description,"
			+ "	a.agree_flg,"
			+ "	p.teams,"
			+ " a.status,"
			+ " g.university,"
			+ " COALESCE(e.score, 0) AS score, "
			+ " COALESCE(e.feedback, '') AS feedback "
			+ "FROM m_applicant a "
			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk "
			+ "JOIN m_group g ON g.applicant_id_pk = a.id_pk "
			+ "LEFT JOIN t_evaluated_applicant e ON e.applicant_id_pk = a.id_pk "
			+ "WHERE a.status IN (:status) "
			+ "AND a.delete_flg = false";
	
	
	public final String GET_APPLICANT_BY_ID_PK = "SELECT e"
			+ " FROM ApplicantEntity e"
			+ " WHERE e.idPk = :applicantIdPk"
			+ " AND e.deleteFlg = false";
	
	public final String GET_APPLICANT_DETAILS_BY_ID_PK = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	a.agree_flg,"
			+ "	p.project_title,"
			+ "	p.project_description,"
			+ "	p.teams, "
			+ "	p.problem_statement,"
			+ "	p.target_market,"
			+ "	p.solution_description,"
			+ "	p.historical_timeline,"
			+ "	p.product_service_offering,"
			+ "	p.pricing_strategy,"
			+ "	p.int_property_status,"
			+ "	p.objectives,"
			+ "	p.scope_proposal,"
			+ "	p.methodology,"
			+ "	p.vitae_file,"
			+ "	p.support_link,"
			+ "	g.group_name,"
			+ "	g.first_name AS leader_first_name,"
			+ "	g.last_name AS leader_last_name,"
			+ "	g.mobile_number,"
			+ "	g.address,"
			+ "	g.university,"
			+ "	gm.first_name AS member_first_name,"
			+ "	gm.last_name AS member_last_name,"
			+ "	a.technology_ans,"
			+ "	a.product_development_ans,"
			+ "	a.competitive_landscape_ans,"
			+ "	a.product_design_ans,"
			+ "	a.team_ans,"
			+ "	a.go_to_market_ans,"
			+ "	a.manufacturing_ans,"
			+ "	a.eligibility_agree_flg,"
			+ "	a.commitment_one_flg,"
			+ "	a.commitment_two_flg,"
			+ "	a.commitment_three_flg,"
			+ "	a.commitment_four_flg,"
			+ " a.status, "
			+ " COALESCE(ea.score, 0) AS score, "
			+ " COALESCE(ea.feedback, '') AS feedback "
			+ "FROM m_applicant a "
			+ "LEFT JOIN t_project p ON p.applicant_id_pk = a.id_pk "
			+ "LEFT JOIN m_group g ON g.applicant_id_pk = a.id_pk "
			+ "LEFT JOIN t_group_member gm ON gm.group_id_pk = g.id_pk "
			+ "LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk "
			+ "WHERE a.id_pk = :applicantIdPk";

	public final String UPDATE_APPLICANT_STATUS = "UPDATE ApplicantEntity a "
			+ "SET a.status = :status "
			+ "WHERE a.idPk IN (:idPks) ";
	
	public final String GET_APPLICANT_BY_CREATED_BY = "SELECT e"
			+ " FROM ApplicantEntity e"
			+ " WHERE e.createdBy = :createdBy"
			+ " AND e.deleteFlg = false";
	
	@Query(value=GET_ALL_APPLICANTS_BY_STATUS, nativeQuery=true)
	public List<Object[]> getAllApplicantByStatusRaw(List<Integer> status) throws DataAccessException;
	
	default List<JoinApplicantProject> getAllApplicantByStatus(List<Integer> status){
		List<Object[]> rawResults = getAllApplicantByStatusRaw(status);
	    List<JoinApplicantProject> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	        JoinApplicantProject applicant = new JoinApplicantProject(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_APPLICANT_BY_ID_PK)
	public ApplicantEntity getApplicantByIdPk(int applicantIdPk) throws DataAccessException;
	
	@Query(value=GET_APPLICANT_DETAILS_BY_ID_PK, nativeQuery=true)
	public List<Object[]> getApplicantDetailsByIdPkRaw(int applicantIdPk) throws DataAccessException;
	
	default List<ApplicantDetailsEntity> getApplicantDetailsByIdPk(int applicantIdPk){
		List<Object[]> rawResults = getApplicantDetailsByIdPkRaw(applicantIdPk);
	    List<ApplicantDetailsEntity> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	ApplicantDetailsEntity applicant = new ApplicantDetailsEntity(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Modifying
	@Transactional
	@Query(value=UPDATE_APPLICANT_STATUS)
	public void updateApplicantStatus(int status, List<Integer> idPks) throws DataAccessException;
	
	@Query(value=GET_APPLICANT_BY_CREATED_BY)
	public ApplicantEntity getApplicantByCreatedBy(int createdBy) throws DataAccessException;
	
}
