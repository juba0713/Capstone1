package capstone.model.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import capstone.model.dao.entity.ProjectEntity;

@Transactional
public interface ProjectDao extends JpaRepository<ProjectEntity, Integer>{
	
	public final String GET_PROJECT_BY_APPLICANT_ID = "SELECT e"	
			+ " FROM ProjectEntity e"
			+ " WHERE e.applicantIdPk = :applicantIdPk"
			+ " AND e.deleteFlg = false";
	
	public final String UPDATE_PROJECT = "UPDATE t_project "
			+ "SET "
			+ "	project_title = :projectTitle,"
			+ "	project_description = :projectDescription,"
			+ "	teams = :teams,"
			+ "	problem_statement = :problemStatement,"
			+ "	target_market = :targetMarket,"
			+ "	solution_description = :solutionDescription,"
			+ "	historical_timeline = :historicalTimeline,"
			+ "	product_service_offering = :productServiceOffering,"
			+ "	pricing_strategy = :pricingStrategy,"
			+ "	int_property_status = :intPropertyStatus,"
			+ "	objectives = :objectives,"
			+ "	scope_proposal = :scopeProposal,"
			+ "	methodology = :methodology,"
			+ "	vitae_file = :vitaeFile,"
			+ "	support_link = :supportLink "
			+ "WHERE "
			+ "	applicant_id_pk = :applicantIdPk "
			+ "AND "
			+ "	delete_flg = false";
	
	@Modifying
	@Query(value=UPDATE_PROJECT, nativeQuery=true)
	public void updateProject(@Param("projectTitle") String projectTitle,
			@Param("projectDescription") String projectDescription,
			@Param("teams") String[] teams,
			@Param("problemStatement") String problemStatement,
			@Param("targetMarket") String targetMarket,
			@Param("solutionDescription") String solutionDescription,
			@Param("historicalTimeline") String[] historicalTimeline,
			@Param("productServiceOffering") String[] productServiceOffering,
			@Param("pricingStrategy") String[] pricingStrategy,
			@Param("intPropertyStatus") String intPropertyStatus,
			@Param("objectives") String objectives,
			@Param("scopeProposal") String scopeProposal,
			@Param("methodology") String methodology,
			@Param("vitaeFile") String vitaeFile,
			@Param("supportLink") String supportLink,
			@Param("applicantIdPk") int applicantIdPk
			) throws DataAccessException;
	
	@Query(value=GET_PROJECT_BY_APPLICANT_ID)
	public ProjectEntity getProjectByApplicantId(int applicantIdPk) throws DataAccessException;
}
