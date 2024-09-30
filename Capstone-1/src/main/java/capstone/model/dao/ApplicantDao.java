package capstone.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantDetailsFeedbackEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.ApplicantMonthly;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserCertificateEntity;
import jakarta.transaction.Transactional;

@Transactional
public interface ApplicantDao extends JpaRepository<ApplicantEntity, Integer>{
	
//	public final String GET_ALL_APPLICANTS_BY_STATUS = "SELECT "
//			+ "	a.id_pk,"
//			+ "	a.email,"
//			+ "	p.project_title,"
//			+ " a.status,"
//			+ " g.university,"
//			+ " COALESCE(e.score, 0) AS score, "
//			+ " COALESCE(e.feedback, '') AS feedback, "
//			+ "( "
//			+ "	CASE "
//			+ "		WHEN a.status > 0 "
//			+ "			THEN (SELECT DISTINCT CONCAT(u2.first_name, ' ', u2.last_name) AS full_name "
//			+ "				  FROM t_accepted_applicant r2 "
//			+ "				  JOIN m_user_information u2 ON u2.id_pk = r2.created_by AND u2.delete_flg = false "
//			+ "				  WHERE r2.applicant_id_pk = a.id_pk"
//			+ "				 AND r2.id_pk = (SELECT MAX(id_pk) FROM t_accepted_applicant WHERE applicant_id_pk = a.id_pk)"
//			+ "				 ) "
//			+ "		ELSE '' "
//			+ "	END "
//			+ "	) AS accepted_by, "
//			+ "		( "
//			+ "	CASE "
//			+ "		WHEN a.status >= 5 "
//			+ "			THEN (SELECT DISTINCT CONCAT(u2.first_name, ' ', u2.last_name) AS full_name "
//			+ "			 FROM t_evaluated_applicant e2 "
//			+ "			 JOIN m_user_information u2 ON u2.id_pk = e2.created_by AND u2.delete_flg = false "
//			+ "			 WHERE e2.applicant_id_pk = a.id_pk"
//			+ "			 AND e2.id_pk = (SELECT MAX(id_pk) FROM t_evaluated_applicant WHERE applicant_id_pk = a.id_pk)"
//			+ "			) "
//			+ "		ELSE '' "
//			+ "	END"
//			+ "	) AS evaluated_by "
//			+ "FROM m_applicant a "
//			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
//			+ "JOIN m_group g ON g.applicant_id_pk = a.id_pk AND g.delete_flg = false "
//			+ "LEFT JOIN ("
//			+ " SELECT e2.id_pk, e2.applicant_id_pk, e2.score, e2.feedback "
//			+ " FROM t_evaluated_applicant e2 WHERE e2.delete_flg = false "
//			+ " UNION ALL "
//			+ " SELECT r2.id_pk, r2.applicant_id_pk, null AS score, feedback "
//			+ " FROM t_rejected_applicant r2 WHERE r2.delete_flg = false "
//			+ ") AS e ON e.applicant_id_pk = a.id_pk "
//			+ "AND e.id_pk = (SELECT MAX(id_pk) FROM t_evaluated_applicant WHERE applicant_id_pk = a.id_pk)  "
//			+ "WHERE a.status IN (:status) "
//			+ "AND a.delete_flg = false";
	
	public final String GET_ALL_APPLICANTS_BY_STATUS = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	p.project_title,"
			+ " a.status,"
			+ " g.university,"
			+ " COALESCE(ed.total, 0) AS totalRating, "
			+ "( "
			+ "	CASE "
			+ "		WHEN a.status > 0 "
			+ "			THEN (SELECT DISTINCT CONCAT(u2.first_name, ' ', u2.last_name) AS full_name "
			+ "				  FROM t_accepted_applicant r2 "
			+ "				  JOIN m_user_information u2 ON u2.id_pk = r2.created_by AND u2.delete_flg = false "
			+ "				  WHERE r2.applicant_id_pk = a.id_pk"
			+ "				 AND r2.delete_flg = false"
			+ "				 ) "
			+ "		ELSE '' "
			+ "	END "
			+ "	) AS accepted_by, "
			+ "		( "
			+ "	CASE "
			+ "		WHEN a.status >= 5 "
			+ "			THEN (SELECT DISTINCT CONCAT(u2.first_name, ' ', u2.last_name) AS full_name "
			+ "			 FROM t_evaluated_applicant e2 "
			+ "			 JOIN m_user_information u2 ON u2.id_pk = e2.created_by AND u2.delete_flg = false "
			+ "			 WHERE e2.applicant_id_pk = a.id_pk"
			+ "			 AND e2.delete_flg = false"
			+ "			) "
			+ "		ELSE '' "
			+ "	END"
			+ "	) AS evaluated_by "
			+ "FROM m_applicant a "
			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
			+ "JOIN m_group g ON g.applicant_id_pk = a.id_pk AND g.delete_flg = false "
			+ " LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "LEFT JOIN ("
			+ " SELECT e2.id_pk, e2.applicant_id_pk"
			+ " FROM t_evaluated_applicant e2 WHERE e2.delete_flg = false "
			+ " UNION ALL "
			+ " SELECT r2.id_pk, r2.applicant_id_pk"
			+ " FROM t_rejected_applicant r2 WHERE r2.delete_flg = false "
			+ ") AS e ON e.applicant_id_pk = a.id_pk "
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
			+ " a.certificate_name,"
			+ " COALESCE(ed.total, 0) as total_rating "
			+ "FROM m_applicant a "
			+ "LEFT JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
			+ "LEFT JOIN m_group g ON g.applicant_id_pk = a.id_pk AND g.delete_flg = false "
			+ "LEFT JOIN t_group_member gm ON gm.group_id_pk = g.id_pk AND gm.delete_flg = false "
			+ "LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "WHERE a.id_pk = :applicantIdPk "
			+ "AND a.delete_flg = false";

	public final String UPDATE_APPLICANT_STATUS = "UPDATE ApplicantEntity a "
			+ "SET a.status = :status "
			+ "WHERE a.idPk IN (:idPks) ";
	
	public final String GET_APPLICANT_BY_CREATED_BY = "SELECT e"
			+ " FROM ApplicantEntity e"
			+ " WHERE e.createdBy = :createdBy"
			+ " AND e.deleteFlg = false";
	
	public final String UPDATE_APPLICANT = "UPDATE m_applicant "
			+ "SET "
			+ "	agree_flg = :agreeFlg,"
			+ "	technology_ans = :technologyAns,"
			+ "	product_development_ans = :productDevelopmentAns,"
			+ "	competitive_landscape_ans = :competitiveLandscapeAns,"
			+ "	product_design_ans = :productDesignAns,"
			+ "	team_ans = :teamAns,"
			+ "	go_to_market_ans = :goToMarketAns,"
			+ "	manufacturing_ans = :manufacturingAns,"
			+ "	eligibility_agree_flg = :eligibilityAgreeFlg,"
			+ "	commitment_one_flg = :commitmentOneFlg,"
			+ "	commitment_two_flg = :commitmentTwoFlg,"
			+ "	commitment_three_flg = :commitmentThreeFlg,"
			+ "	commitment_four_flg = :commitmentFourFlg,"
			+ "	status = :status "
			+ "WHERE"
			+ "	id_pk = :idPk "
			+ "AND "
			+ "	delete_flg = false";
	
	public final String DELETE_APPLICANT_BY_CREATED_BY = "UPDATE m_applicant "
			+ "SET "
			+ "	delete_flg = true "
			+ "WHERE"
			+ "	created_by = :createdBy "
			+ "AND "
			+ "	delete_flg = false";
	
	public final String UPDATE_APPLICANT_CERTIFICATE = "UPDATE m_applicant "
			+ "SET "
			+ "	certificate_name = :certificateName,"
			+ "	status = 50 "
			+ "WHERE"
			+ "	id_pk = :applicantIdPk "
			+ "AND "
			+ "	delete_flg = false";
	
	@Modifying
	@Query(value=UPDATE_APPLICANT, nativeQuery=true)
	public void updateApplicant(@Param("agreeFlg") Boolean agreeFlg,
				@Param("technologyAns") int technologyAns,
				@Param("productDevelopmentAns") int productDevelopmentAns,
				@Param("competitiveLandscapeAns") int competitiveLandscapeAns,
				@Param("productDesignAns") int productDesignAns,
				@Param("teamAns") int teamAns,
				@Param("goToMarketAns") int goToMarketAns,
				@Param("manufacturingAns") int manufacturingAns,
				@Param("eligibilityAgreeFlg") Boolean eligibilityAgreeFlg,
				@Param("commitmentOneFlg") Boolean commitmentOneFlg,
				@Param("commitmentTwoFlg") Boolean commitmentTwoFlg,
				@Param("commitmentThreeFlg") Boolean commitmentThreeFlg,
				@Param("commitmentFourFlg") Boolean commitmentFourFlg,
				@Param("status") int status,
				@Param("idPk") int idPk
			) throws DataAccessException;
	
	public final String GET_APPLICANT_ON_TODAY_MONTH  = "SELECT a.id_pk,"
			+ "p.project_title 	 "
			+ "FROM m_applicant a "
			+ "LEFT JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
			+ " LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "WHERE EXTRACT(MONTH FROM a.created_date) = EXTRACT(MONTH FROM CURRENT_DATE) "
			+ "  AND EXTRACT(YEAR FROM a.created_date) = EXTRACT(YEAR FROM CURRENT_DATE)"
			+ "  AND a.status IN (5,50)"
			+ " AND ed.total >= 60 ";
	
	public final String GET_APPLICANT_RANKING_ON_TODAY_MONTH = "SELECT a.id_pk, "
			+ "       p.project_title "
			+ "FROM m_applicant a "
			+ "LEFT JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
			+ "LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "WHERE EXTRACT(MONTH FROM a.created_date) = EXTRACT(MONTH FROM CURRENT_DATE) "
			+ "  AND EXTRACT(YEAR FROM a.created_date) = EXTRACT(YEAR FROM CURRENT_DATE) "
			+ "  AND a.status IN (8) "
			+ " ORDER BY ed.total ASC; "
			+ "";
	
	public final String GET_APPLICANT_RANKING_BY_YEAR_MONTH = "SELECT a.id_pk, "
			+ "       p.project_title "
			+ "FROM m_applicant a "
			+ "LEFT JOIN t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false "
			+ "LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "WHERE EXTRACT(MONTH FROM a.created_date) = :month "
			+ "  AND EXTRACT(YEAR FROM a.created_date) = :year "
			+ "  AND a.status IN (8) "
			+ " ORDER BY ed.total ASC; "
			+ "";
	
	public final String GET_APPLICANT_DETAILS_WITH_FEEDBACKS = "SELECT   "
			+ "    a.id_pk,   "
			+ "    a.email,   "
			+ "    a.agree_flg,   "
			+ "    p.project_title,   "
			+ "    p.project_description,   "
			+ "    p.teams,   "
			+ "    p.problem_statement,   "
			+ "    p.target_market,   "
			+ "    p.solution_description,   "
			+ "    p.historical_timeline,   "
			+ "    p.product_service_offering,   "
			+ "    p.pricing_strategy,   "
			+ "    p.int_property_status,   "
			+ "    p.objectives,   "
			+ "    p.scope_proposal,   "
			+ "    p.methodology,   "
			+ "    p.vitae_file,   "
			+ "    p.support_link,   "
			+ "    g.group_name,   "
			+ "    g.first_name AS leader_first_name,   "
			+ "    g.last_name AS leader_last_name,   "
			+ "    g.mobile_number,   "
			+ "    g.address,   "
			+ "    g.university,   "
			+ "    gm.first_name AS member_first_name,   "
			+ "    gm.last_name AS member_last_name,   "
			+ "    a.technology_ans,   "
			+ "    a.product_development_ans,   "
			+ "    a.competitive_landscape_ans,   "
			+ "    a.product_design_ans,   "
			+ "    a.team_ans,   "
			+ "    a.go_to_market_ans,   "
			+ "    a.manufacturing_ans,   "
			+ "    a.eligibility_agree_flg,   "
			+ "    a.commitment_one_flg,   "
			+ "    a.commitment_two_flg,   "
			+ "    a.commitment_three_flg,   "
			+ "    a.commitment_four_flg,   "
			+ "    a.status,   "
			+ "    a.certificate_name,   "
			+ "    d.ct_one_flg,   "
			+ "    d.ct_one_comments,   "
			+ "    d.ct_two_flg,   "
			+ "    d.ct_two_comments,   "
			+ "    d.ct_three_flg,   "
			+ "    d.ct_three_comments,   "
			+ "    d.ct_four_flg,   "
			+ "    d.ct_four_comments,   "
			+ "    d.ct_five_flg,   "
			+ "    d.ct_five_comments,   "
			+ "    d.ct_six_flg,   "
			+ "    d.ct_six_comments,   "
			+ "    d.ct_seven_flg,   "
			+ "    d.ct_seven_comments,   "
			+ "    d.ct_eight_flg,   "
			+ "    d.ct_eight_comments,   "
			+ "    d.ct_nine_flg,   "
			+ "    d.ct_nine_comments,   "
			+ "    d.recommendation,   "
			+ "    COALESCE(ed.ct_one_rating, 0) AS ct_one_rating,   "
			+ "    ed.ct_one_comments,   "
			+ "    COALESCE(ed.ct_two_rating, 0) AS ct_two_rating,   "
			+ "    ed.ct_two_comments,   "
			+ "    COALESCE(ed.ct_three_rating, 0) AS ct_three_rating,   "
			+ "    ed.ct_three_comments,   "
			+ "    COALESCE(ed.ct_four_rating, 0) AS ct_four_rating,   "
			+ "    ed.ct_four_comments,   "
			+ "    COALESCE(ed.ct_five_rating, 0) AS ct_five_rating,   "
			+ "    ed.ct_five_comments,   "
			+ "    COALESCE(ed.ct_six_rating, 0) AS ct_six_rating,   "
			+ "    ed.ct_six_comments,   "
			+ "    COALESCE(ed.ct_seven_rating, 0) AS ct_seven_rating,   "
			+ "    ed.ct_seven_comments,   "
			+ "    COALESCE(ed.ct_eight_rating, 0) AS ct_eight_rating,   "
			+ "    ed.ct_eight_comments,   "
			+ "    ed.tbi_feedback   "
			+ "FROM   "
			+ "    m_applicant a   "
			+ "LEFT JOIN   "
			+ "    t_project p ON p.applicant_id_pk = a.id_pk AND p.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    m_group g ON g.applicant_id_pk = a.id_pk AND g.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    t_group_member gm ON gm.group_id_pk = g.id_pk AND gm.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    t_rejected_applicant r ON a.status = 2 AND r.applicant_id_pk = a.id_pk AND r.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    t_accepted_applicant ap ON a.status != 2 AND ap.applicant_id_pk = a.id_pk AND ap.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    t_prescreen_details d ON   "
			+ "        (a.status = 2 AND d.rejected_applicant_id_pk = r.id_pk AND d.delete_flg = false)  "
			+ "        OR   "
			+ "        (a.status != 2 AND d.accepted_applicant_id_pk = ap.id_pk AND d.delete_flg = false)  "
			+ "LEFT JOIN   "
			+ "    t_evaluated_applicant ea ON ea.applicant_id_pk = a.id_pk AND ea.delete_flg = false   "
			+ "LEFT JOIN   "
			+ "    t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false   "
			+ "WHERE   "
			+ "    a.id_pk = :idPk  "
			+ "    AND a.delete_flg = false;";
	
	public final String GET_USER_INFORMATION_FOR_CERTIFICATE = "SELECT "
			+ "	u.id_pk AS user_id_pk, "
			+ "	u.first_name, "
			+ "	u.last_name, "
			+ "	u.email, "
			+ "	ed.total "
			+ "FROM "
			+ "	m_applicant s "
			+ "LEFT JOIN "
			+ "	m_user_information u ON u.id_pk = s.created_by AND u.delete_flg = false "
			+ "LEFT JOIN "
			+ "	t_evaluated_applicant ea ON ea.applicant_id_pk = s.id_pk AND ea.delete_flg = false "
			+ "LEFT JOIN "
			+ "	t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false "
			+ "WHERE "
			+ "	s.id_pk = :applicantIdPk";
	
	@Query(value=GET_USER_INFORMATION_FOR_CERTIFICATE, nativeQuery=true)
	public List<Object[]> getUserInformationForCertificateRaw(int applicantIdPk) throws DataAccessException;
	
	default UserCertificateEntity getUserInformationForCertificate(int applicantIdPk){
		
		List<Object[]> rawResults = getUserInformationForCertificateRaw(applicantIdPk);
	  
		UserCertificateEntity applicant = new UserCertificateEntity(rawResults.get(0));  
   
	    return applicant;
	}
	
	@Query(value=GET_APPLICANT_DETAILS_WITH_FEEDBACKS, nativeQuery=true)
	public List<Object[]> getApplicantDetailsWithFeedbackRaw(int idPk) throws DataAccessException;
	
	default List<ApplicantDetailsFeedbackEntity> getApplicantDetailsWithFeedback(int idPk){
		List<Object[]> rawResults = getApplicantDetailsWithFeedbackRaw(idPk);
	    List<ApplicantDetailsFeedbackEntity> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	ApplicantDetailsFeedbackEntity applicant = new ApplicantDetailsFeedbackEntity(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_APPLICANT_ON_TODAY_MONTH, nativeQuery=true)
	public List<Object[]> getApplicantOnTodayMonthRaw() throws DataAccessException;
	
	default List<ApplicantMonthly> getApplicantOnTodayMonth(){
		List<Object[]> rawResults = getApplicantOnTodayMonthRaw();
	    List<ApplicantMonthly> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	ApplicantMonthly applicant = new ApplicantMonthly(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_APPLICANT_RANKING_ON_TODAY_MONTH, nativeQuery=true)
	public List<Object[]> getApplicantRankingOnTodayMonthRaw() throws DataAccessException;
	
	default List<ApplicantMonthly> getApplicantRankingOnTodayMonth(){
		List<Object[]> rawResults = getApplicantRankingOnTodayMonthRaw();
	    List<ApplicantMonthly> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	ApplicantMonthly applicant = new ApplicantMonthly(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_APPLICANT_RANKING_BY_YEAR_MONTH, nativeQuery=true)
	public List<Object[]> getApplicantRankingByYearMonthRaw(int month, int year) throws DataAccessException;
	
	default List<ApplicantMonthly> getApplicantRankingByYearMonth(int month, int year){
		List<Object[]> rawResults = getApplicantRankingByYearMonthRaw(month, year);
	    List<ApplicantMonthly> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	    	ApplicantMonthly applicant = new ApplicantMonthly(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}

	@Modifying
	@Query(value=DELETE_APPLICANT_BY_CREATED_BY, nativeQuery=true)
	public void deleteApplicantByCreatedBy(@Param("createdBy") int createdBy) throws DataAccessException;;
	
	@Modifying
	@Query(value=UPDATE_APPLICANT_CERTIFICATE, nativeQuery=true)
	public void updateApplicantCertificate(@Param("certificateName") String certificateName,
			@Param("applicantIdPk") int applicantIdPk
			) throws DataAccessException;
	
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
