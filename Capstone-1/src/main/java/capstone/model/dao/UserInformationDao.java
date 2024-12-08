package capstone.model.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.ManagerDashboardEntity;
import capstone.model.dao.entity.MonthlyHighestScoresEntity;
import capstone.model.dao.entity.MonthlyTotalApplicationEntity;
import capstone.model.dao.entity.OfficerDashboardEntity;
import capstone.model.dao.entity.PerformanceMetricsEntity;
import capstone.model.dao.entity.TbiBoardDashboardEntity;
import capstone.model.dao.entity.UserDetailsEntity;
import capstone.model.dao.entity.UserInformationEntity;

@Transactional
public interface UserInformationDao extends JpaRepository<UserInformationEntity, Integer> {

	public final String GET_USER_BY_USERNAME = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.email = :email"
			+ " AND e.deleteFlg = false";

	public final String GET_USER_BY_ID_PK = "SELECT e"
			+ " FROM UserInformationEntity e"
			+ " WHERE e.idPk = :idPk"
			+ " AND e.deleteFlg = false";

	public final String GET_USER_BY_EVALUATED_TOKEN = "SELECT u "
			+ "FROM EvaluatedApplicantEntity e "
			+ "JOIN ApplicantEntity a ON a.idPk = e.applicantIdPk "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy "
			+ "WHERE e.token = :token "
			+ "AND e.deleteFlg = false";

	public final String GET_USER_BY_REJECTED_TOKEN = "SELECT u "
			+ "FROM RejectedApplicantEntity e "
			+ "JOIN ApplicantEntity a ON a.idPk = e.applicantIdPk "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy "
			+ "WHERE e.token = :token "
			+ "AND e.deleteFlg = false";

	public final String GET_USER_BY_APPLICANT_ID_PK = "SELECT u "
			+ "FROM ApplicantEntity a "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy AND u.deleteFlg = false "
			+ "WHERE a.idPk = :applicantIdPk "
			+ "AND a.deleteFlg = false";

	public final String GET_DETAILS_FOR_ADMIN_DASHBOARD = "SELECT CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_evaluated_applicant e	 "
			+ "  LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = e.id_pk AND ed.delete_flg = false "
			+ "  WHERE ed.total >= 60 "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS application_passed_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_evaluated_applicant e "
			+ "  LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = e.id_pk AND ed.delete_flg = false "
			+ "  WHERE ed.total < 60 "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS application_failed_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_accepted_applicant e "
			+ ") AS INTEGER) AS application_accepted_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_rejected_applicant e "
			+ "  WHERE e.resubmit_flg = false "
			+ ") AS INTEGER) AS application_rejected_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_applicant e "
			+ "  WHERE e.status IN (0) "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS in_officer_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_applicant e "
			+ "  WHERE e.status IN (4) "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS in_tbiboard_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_applicant e "
			+ "  WHERE e.status IN (3,4) "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS in_manager_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_user_information e "
			+ "  WHERE e.role = 'APPLICANT' "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS applicant_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_user_information e "
			+ "  WHERE e.role = 'OFFICER' "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS officer_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_user_information e "
			+ "  WHERE e.role = 'TBIBOARD' "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS tbiboard_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_user_information e "
			+ "  WHERE e.role = 'MANAGER' "
			+ "  AND e.delete_flg = false "
			+ ") AS INTEGER) AS manager_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM m_user_information e "
			+ "  WHERE e.delete_flg = false "
			+ ") AS INTEGER) AS activated_account_count, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_rejected_applicant e "
			+ "  WHERE e.resubmit_flg = true "
			+ ") AS INTEGER) AS reapplication_rejected, "
			+ "CAST( ( "
			+ "  SELECT COUNT(e) "
			+ "  FROM t_evaluated_applicant e "
			+ "  WHERE e.resubmit_flg = true "
			+ ") AS INTEGER) AS reapplication_failed,"
			+ "CAST( ( "
			+ "        SELECT COUNT(e) "
			+ "        FROM m_applicant e "
			+ "        WHERE e.delete_flg = false "
			+ "		AND e.certificate_name IS NOT NULL "
			+ "    ) AS INTEGER) AS issued_certifate,"
			+ "COALESCE(CAST(( "
			+ "		SELECT SUM(e.submission_count) "
			+ "		FROM m_applicant e "
			+ ") AS INTEGER), 0) AS submission_count; ";

	public final String GET_DETAILS_FOR_TBI_DASHBOARD = "SELECT   "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.status IN ('4', '40')  "
			+ "        AND e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS pending_applicant_count,  "
			+ "  "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_evaluated_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER),0) AS evaluated_applicant_count,  "
			+ "       COALESCE(CAST( (  "
			+ "        SELECT (COUNT(e) * 100.0) /   "
			+ "               NULLIF((SELECT COUNT(ee) FROM m_applicant ee WHERE ee.delete_flg = false), 0)  "
			+ "        FROM t_accepted_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER),0) AS acceptance_rate,  "
			+ "      "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT (COUNT(e) * 100.0) /   "
			+ "               NULLIF((SELECT COUNT(ee) FROM m_applicant ee WHERE ee.delete_flg = false), 0)  "
			+ "        FROM t_rejected_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER) ,0)AS rejection_rate ";

	public final String GET_DETAILS_FOR_OFFICER_DASHBOARD = "SELECT   "
			+ "    CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.status IN ('0')  "
			+ "        AND e.delete_flg = false  "
			+ "    ) AS INTEGER) AS pending_applicant_count,  "
			+ "    CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_accepted_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER) AS accepted_applicant_count,  "
			+ "	CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_rejected_applicant e  "
			+ "		WHERE e.resubmit_flg = true  "
			+ "    ) AS INTEGER) AS rejected_applicant_yes_count,  "
			+ "	CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_rejected_applicant e  "
			+ "		WHERE e.resubmit_flg = false  "
			+ "    ) AS INTEGER) AS rejected_applicant_no_count,  "
			+ "	CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "		INNER JOIN t_rejected_applicant r ON r.applicant_id_pk = e.id_pk  "
			+ "		WHERE r.resubmit_flg = true  "
			+ "		AND e.status IN (1,3,4,40,5,50,6,7,8)  "
			+ "    ) AS INTEGER) AS resubmitted_applicant_count";

	public final String GET_DETAILS_FOR_MANAGER_DASHBOARD = "SELECT   "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS total_applications_count,  "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "		AND e.status = 0  "
			+ "    ) AS INTEGER), 0) AS in_officer_count,  "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "		AND e.status = 4  "
			+ "    ) AS INTEGER), 0) AS in_tbiboard_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "		AND e.certificate_name IS NOT NULL  "
			+ "    ) AS INTEGER), 0) AS issued_certificate_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "		LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = e.id_pk AND ea.delete_flg = false  "
			+ "		LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false	  "
			+ "        WHERE e.delete_flg = false  "
			+ "		AND ed.total >= 60  "
			+ "    ) AS INTEGER), 0) AS passed_applications_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "		LEFT JOIN t_evaluated_applicant ea ON ea.applicant_id_pk = e.id_pk AND ea.delete_flg = false  "
			+ "		LEFT JOIN t_evaluation_details ed ON ed.evaluated_applicant_id_pk = ea.id_pk AND ed.delete_flg = false	  "
			+ "        WHERE e.delete_flg = false  "
			+ "		AND ed.total < 60  "
			+ "    ) AS INTEGER), 0) AS failed_applications_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_accepted_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS accepted_applications_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_evaluated_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS evaluated_applications_count,  "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT (COUNT(e) * 100.0) /   "
			+ "               NULLIF((SELECT COUNT(ee) FROM m_applicant ee WHERE ee.delete_flg = false), 0)  "
			+ "        FROM t_accepted_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS acceptance_rate,  "
			+ "      "
			+ "    COALESCE(CAST( (  "
			+ "        SELECT (COUNT(e) * 100.0) /   "
			+ "               NULLIF((SELECT COUNT(ee) FROM m_applicant ee WHERE ee.delete_flg = false), 0)  "
			+ "        FROM t_rejected_applicant e  "
			+ "        WHERE e.delete_flg = false  "
			+ "    ) AS INTEGER), 0) AS rejection_rate,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM m_applicant e  "
			+ "		INNER JOIN t_rejected_applicant r ON r.applicant_id_pk = e.id_pk	  "
			+ "    ) AS INTEGER), 0) AS resubmitted_applicantions_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_rejected_applicant e  "
			+ "		WHERE e.resubmit_flg = true  "
			+ "    ) AS INTEGER), 0) AS rejected_application_eligible_count,  "
			+ "	COALESCE(CAST( (  "
			+ "        SELECT COUNT(e)  "
			+ "        FROM t_rejected_applicant e  "
			+ "		WHERE e.resubmit_flg = false  "
			+ "    ) AS INTEGER), 0) AS rejected_application_not_eligible_count";

	public final String GET_MONTHLY_HIGHEST_SCORES_FOR_MANAGER_ANALYTICS = "WITH months AS ( "
			+ "    SELECT generate_series(1, 12) AS month "
			+ ")  "
			+ "SELECT   "
			+ "    m.month,  "
			+ "    COALESCE(MAX(ed.total), 0) AS highest_total  "
			+ "FROM   "
			+ "    months m  "
			+ "LEFT JOIN   "
			+ "    t_evaluated_applicant ea   "
			+ "    ON EXTRACT(MONTH FROM ea.created_date) = m.month  "
			+ "LEFT JOIN   "
			+ "    t_evaluation_details ed   "
			+ "    ON ed.evaluated_applicant_id_pk = ea.id_pk  "
			+ "    AND EXTRACT(YEAR FROM ea.created_date) = 2024  "
			+ "GROUP BY   "
			+ "    m.month  "
			+ "ORDER BY   "
			+ "    m.month;  "
			+ "";

	public final String GET_OFFICER_PERFORMANCE_METRICS_FOR_MANAGER_ANALYTICS = "SELECT CONCAT(u.first_name, ' ', u.last_name) AS full_name, CAST(COUNT(*) AS INTEGER) AS review_count "
			+ "FROM (  "
			+ "    SELECT a.created_by  "
			+ "    FROM t_accepted_applicant a  "
			+ "    UNION ALL  "
			+ "    SELECT r.created_by  "
			+ "    FROM t_rejected_applicant r  "
			+ ") AS combined  "
			+ "LEFT JOIN m_user_information u ON u.id_pk = combined.created_by  "
			+ "GROUP BY u.first_name, u.last_name  "
			+ "ORDER BY review_count DESC  "
			+ "LIMIT 3;  "
			+ "";

	public final String GET_TBI_PERFORMANCE_METRICS_FOR_MANAGER_ANALYTICS = "SELECT CONCAT(u.first_name, ' ', u.last_name) AS full_name, CAST(COUNT(*) AS INTEGER) AS review_count  "
			+ "FROM t_evaluated_applicant e  "
			+ "LEFT JOIN m_user_information u ON u.id_pk = e.created_by  "
			+ "GROUP BY u.first_name, u.last_name  "
			+ "ORDER BY review_count DESC  "
			+ "LIMIT 3;  "
			+ "";

	public final String GET_MONTHLY_ACCEPTED_APPLICATION_FOR_MANAGER_ANALYTICS = "WITH months AS (  "
			+ "    SELECT generate_series(1, 12) AS month  "
			+ ")  "
			+ "SELECT   "
			+ "    m.month,  "
			+ "    CAST( COALESCE(COUNT(a.id_pk), 0) AS INTEGER) AS total  "
			+ "FROM   "
			+ "    months m  "
			+ "LEFT JOIN   "
			+ "    t_accepted_applicant a   "
			+ "    ON EXTRACT(MONTH FROM a.created_date) = m.month  "
			+ "    AND EXTRACT(YEAR FROM a.created_date) = 2024  "
			+ "GROUP BY   "
			+ "    m.month  "
			+ "ORDER BY   "
			+ "    m.month;  "
			+ "";

	public final String GET_MONTHLY_REJECTED_APPLICATION_FOR_MANAGER_ANALYTICS = "WITH months AS (  "
			+ "    SELECT generate_series(1, 12) AS month  "
			+ ")  "
			+ "SELECT   "
			+ "    m.month,  "
			+ "   CAST( COALESCE(COUNT(a.id_pk), 0) AS INTEGER) AS total  "
			+ "FROM   "
			+ "    months m  "
			+ "LEFT JOIN   "
			+ "    t_rejected_applicant  a   "
			+ "    ON EXTRACT(MONTH FROM a.created_date) = m.month  "
			+ "    AND EXTRACT(YEAR FROM a.created_date) = 2024  "
			+ "GROUP BY   "
			+ "    m.month  "
			+ "ORDER BY   "
			+ "    m.month;  "
			+ "";

	public final String GET_USERS_BY_APPLICANT_ID_PKS = "SELECT u "
			+ "FROM ApplicantEntity a "
			+ "JOIN UserInformationEntity u ON u.idPk = a.createdBy AND u.deleteFlg = false "
			+ "WHERE a.idPk IN (:applicantIdPks) "
			+ "AND a.deleteFlg = false";

	public final String GET_ALL_USERS = "SELECT e.id_pk,  "
			+ "e.email, "
			+ "CONCAT(e.first_name, ' ', e.last_name) AS fullName, "
			+ "e.mobile_number, "
			+ "e.role, "
			+ "e.created_date, "
			+ "e.updated_date, "
			+ "NOT ((SELECT COUNT(a.created_by)>0  "
			+ " FROM t_accepted_applicant a  "
			+ " WHERE a.created_by = e.id_pk  "
			+ " AND e.delete_flg = false) "
			+ " OR "
			+ " (SELECT COUNT(a.created_by)>0  "
			+ " FROM t_rejected_applicant a  "
			+ " WHERE a.created_by = e.id_pk  "
			+ " AND e.delete_flg = false) "
			+ " OR "
			+ " (SELECT COUNT(a.created_by)>0  "
			+ " FROM t_evaluated_applicant a 	 "
			+ " WHERE a.created_by = e.id_pk  "
			+ " AND e.delete_flg = false) "
			+ " OR "
			+ " (SELECT COUNT(u)>0  "
			+ "  FROM m_user_information u "
			+ "  WHERE u.role IN ('APPLICANT', 'ADMIN') "
			+ "  AND u.id_pk = e.id_pk) "
			+ " ) "
			+ " AS deletable,"
			+ " e.block_flg AS block_flg "
			+ "FROM m_user_information e "
			+ "WHERE e.delete_flg = false";

	public final String DELETE_USER = "UPDATE m_user_information  "
			+ "SET "
			+ "	delete_flg = true "
			+ "WHERE "
			+ "	id_pk = :userIdPk ";

	public final String UPDATE_USER = "UPDATE m_user_information "
			+ "SET "
			+ "    first_name = :firstName, "
			+ "    last_name = :lastName, "
			+ "    mobile_number = :mobileNumber, "
			+ "    role = :role, "
			+ "    updated_date = :updatedDate "
			+ "WHERE "
			+ "    id_pk = :userIdPk";

	String UPDATE_USER_BLOCK_STATUS = "UPDATE m_user_information "
			+ "SET block_flg = :status "
			+ "WHERE id_pk = :userIdPk ";

	@Modifying
	@Query(value = UPDATE_USER_BLOCK_STATUS, nativeQuery = true)
	public int updateUserBlockStatus(@Param("userIdPk") int userIdPk, @Param("status") boolean status)
			throws DataAccessException;

	@Modifying
	@Query(value = DELETE_USER, nativeQuery = true)
	public void deleteUser(@Param("userIdPk") int userIdPk) throws DataAccessException;

	@Modifying
	@Query(value = UPDATE_USER, nativeQuery = true)
	public int updateUser(@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			@Param("mobileNumber") String mobileNumber,
			@Param("role") String role,
			@Param("updatedDate") Timestamp updatedDate,
			@Param("userIdPk") int userIdPk) throws DataAccessException;

	@Query(value = GET_USER_BY_APPLICANT_ID_PK)
	public UserInformationEntity getUserByApplicantIdPk(int applicantIdPk) throws DataAccessException;

	@Query(value = GET_USERS_BY_APPLICANT_ID_PKS)
	public List<UserInformationEntity> getUsersByApplicantIdPks(List<Integer> applicantIdPks)
			throws DataAccessException;

	@Query(value = GET_USER_BY_EVALUATED_TOKEN)
	public UserInformationEntity getUserByEvaluatedtoken(String token) throws DataAccessException;

	@Query(value = GET_USER_BY_REJECTED_TOKEN)
	public UserInformationEntity getUserByRejectedtoken(String token) throws DataAccessException;

	@Query(value = GET_USER_BY_USERNAME)
	public UserInformationEntity getUserByUsername(String email) throws DataAccessException;

	@Query(value = GET_USER_BY_ID_PK)
	public UserInformationEntity getUserByIdPk(int idPk) throws DataAccessException;

	@Query(value = GET_ALL_USERS, nativeQuery = true)
	public List<Object[]> getAllUsersRaw() throws DataAccessException;

	default List<UserDetailsEntity> getAllUsers() {

		List<Object[]> rawResults = getAllUsersRaw();
		List<UserDetailsEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			UserDetailsEntity applicant = new UserDetailsEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	@Query(value = GET_DETAILS_FOR_ADMIN_DASHBOARD, nativeQuery = true)
	public List<Object[]> getDetailsForAdminRaw() throws DataAccessException;

	default AdminDashboardEntity getDetailsForAdmiDashboard() {

		Object[] data = getDetailsForAdminRaw().get(0);

		AdminDashboardEntity entity = new AdminDashboardEntity(data);

		return entity;
	};

	@Query(value = GET_DETAILS_FOR_TBI_DASHBOARD, nativeQuery = true)
	public List<Object[]> getDetailsForTbiBoardRaw() throws DataAccessException;

	default TbiBoardDashboardEntity getDetailsForTbiBoardDashboard() {

		Object[] data = getDetailsForTbiBoardRaw().get(0);

		TbiBoardDashboardEntity entity = new TbiBoardDashboardEntity(data);

		return entity;
	};

	@Query(value = GET_DETAILS_FOR_OFFICER_DASHBOARD, nativeQuery = true)
	public List<Object[]> getDetailsForOfficerRaw() throws DataAccessException;

	default OfficerDashboardEntity getDetailsForOfficerDashboard() {

		Object[] data = getDetailsForOfficerRaw().get(0);

		OfficerDashboardEntity entity = new OfficerDashboardEntity(data);

		return entity;
	};

	@Query(value = GET_DETAILS_FOR_MANAGER_DASHBOARD, nativeQuery = true)
	public List<Object[]> getDetailsForManagerRaw() throws DataAccessException;

	default ManagerDashboardEntity getDetailsForManagerDashboard() {

		Object[] data = getDetailsForManagerRaw().get(0);

		ManagerDashboardEntity entity = new ManagerDashboardEntity(data);

		return entity;
	};

	@Query(value = GET_MONTHLY_HIGHEST_SCORES_FOR_MANAGER_ANALYTICS, nativeQuery = true)
	public List<Object[]> getMonthlyHighestScoresRaw() throws DataAccessException;

	default List<MonthlyHighestScoresEntity> getMonthlyHighestScores() {

		List<Object[]> rawResults = getMonthlyHighestScoresRaw();
		List<MonthlyHighestScoresEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			MonthlyHighestScoresEntity applicant = new MonthlyHighestScoresEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	@Query(value = GET_OFFICER_PERFORMANCE_METRICS_FOR_MANAGER_ANALYTICS, nativeQuery = true)
	public List<Object[]> getOfficerPerformanceMetricsRaw() throws DataAccessException;

	default List<PerformanceMetricsEntity> getOfficerPerformanceMetrics() {

		List<Object[]> rawResults = getOfficerPerformanceMetricsRaw();
		List<PerformanceMetricsEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			PerformanceMetricsEntity applicant = new PerformanceMetricsEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	@Query(value = GET_TBI_PERFORMANCE_METRICS_FOR_MANAGER_ANALYTICS, nativeQuery = true)
	public List<Object[]> getTbiBoardPerformanceMetricsRaw() throws DataAccessException;

	default List<PerformanceMetricsEntity> getTbiBoardPerformanceMetrics() {

		List<Object[]> rawResults = getTbiBoardPerformanceMetricsRaw();
		List<PerformanceMetricsEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			PerformanceMetricsEntity applicant = new PerformanceMetricsEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	@Query(value = GET_MONTHLY_ACCEPTED_APPLICATION_FOR_MANAGER_ANALYTICS, nativeQuery = true)
	public List<Object[]> getMonthlyAcceptedApplicationsRaw() throws DataAccessException;

	default List<MonthlyTotalApplicationEntity> getMonthlyAcceptedApplications() {

		List<Object[]> rawResults = getMonthlyAcceptedApplicationsRaw();
		List<MonthlyTotalApplicationEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			MonthlyTotalApplicationEntity applicant = new MonthlyTotalApplicationEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	@Query(value = GET_MONTHLY_REJECTED_APPLICATION_FOR_MANAGER_ANALYTICS, nativeQuery = true)
	public List<Object[]> getMonthlyRejectedApplicationsRaw() throws DataAccessException;

	default List<MonthlyTotalApplicationEntity> getMonthlyRejectedApplications() {

		List<Object[]> rawResults = getMonthlyRejectedApplicationsRaw();
		List<MonthlyTotalApplicationEntity> users = new ArrayList<>();

		for (Object[] objects : rawResults) {
			MonthlyTotalApplicationEntity applicant = new MonthlyTotalApplicationEntity(objects);
			users.add(applicant);
		}

		return users;
	};

	public final String COUNT_ADMIN_USER = "SELECT COUNT(*) FROM m_user_information WHERE role = 'ADMIN'";

	@Query(value = COUNT_ADMIN_USER, nativeQuery = true)
	int countAdminUsers();

}
