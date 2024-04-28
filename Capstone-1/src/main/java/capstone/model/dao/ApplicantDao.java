package capstone.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;

public interface ApplicantDao extends JpaRepository<ApplicantEntity, Integer>{
	
	public final String GET_ALL_APPLICANTS_0 = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	p.project_title,"
			+ "	p.project_description,"
			+ "	a.agree_flg,"
			+ "	p.teams,"
			+ " a.status "
			+ "FROM m_applicant a "
			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk "
			+ "WHERE a.status = 0 "
			+ "AND a.delete_flg = false";
	
	
	public final String GET_ALL_APPLICANTS_1_3 = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	p.project_title,"
			+ "	p.project_description,"
			+ "	a.agree_flg,"
			+ "	p.teams,"
			+ " a.status "
			+ "FROM m_applicant a "
			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk "
			+ "WHERE a.status = 1 OR a.status = 3 "
			+ "AND a.delete_flg = false";
	
	public final String GET_APPLICANT_BY_ID_PK = "SELECT e"
			+ " FROM ApplicantEntity e"
			+ " WHERE e.idPk = :applicantIdPk"
			+ " AND e.deleteFlg = false";

	
	@Query(value=GET_ALL_APPLICANTS_0, nativeQuery=true)
	public List<Object[]> getAllApplicant0Raw() throws DataAccessException;
	
	default List<JoinApplicantProject> getAllApplicant0(){
		List<Object[]> rawResults = getAllApplicant0Raw();
	    List<JoinApplicantProject> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	        JoinApplicantProject applicant = new JoinApplicantProject(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_ALL_APPLICANTS_1_3, nativeQuery=true)
	public List<Object[]> getAllApplicant13Raw() throws DataAccessException;
	
	default List<JoinApplicantProject> getAllApplicant13(){
		List<Object[]> rawResults = getAllApplicant13Raw();
	    List<JoinApplicantProject> applicants = new ArrayList<>();

	    for (Object[] objects : rawResults) {
	        JoinApplicantProject applicant = new JoinApplicantProject(objects);  
	        applicants.add(applicant);
	    }

	    return applicants;
	}
	
	@Query(value=GET_APPLICANT_BY_ID_PK)
	public ApplicantEntity getApplicantByIdPk(int applicantIdPk) throws DataAccessException;
}
