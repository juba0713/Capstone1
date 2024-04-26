package capstone.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;

public interface ApplicantDao extends JpaRepository<ApplicantEntity, Integer>{
	
	public final String GET_ALL_APPLICANTS = "SELECT "
			+ "	a.id_pk,"
			+ "	a.email,"
			+ "	p.project_title,"
			+ "	p.project_description,"
			+ "	a.agree_flg,"
			+ "	p.teams "
			+ "FROM m_applicant a "
			+ "JOIN t_project p ON p.applicant_id_pk = a.id_pk "
			+ "WHERE a.delete_flg = false";
	
	public final String GET_APPLICANT_BY_ID_PK = "SELECT e"
			+ " FROM ApplicantEntity e"
			+ " WHERE e.idPk = :applicantIdPk"
			+ " AND e.deleteFlg = false";

	
	@Query(value=GET_ALL_APPLICANTS, nativeQuery=true)
	public List<Object[]> getAllApplicantRaw() throws DataAccessException;
	
	default List<JoinApplicantProject> getAllApplicant(){
		List<Object[]> rawResults = getAllApplicantRaw();
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
