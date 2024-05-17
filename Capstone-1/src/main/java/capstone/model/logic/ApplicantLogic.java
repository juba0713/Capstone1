package capstone.model.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.RejectedApplicantEntity;


@Service
public interface ApplicantLogic {

	/**
	 * To save applicant entity
	 * @param entity
	 * @return int
	 */
	public int saveApplicantEntity(ApplicantEntity entity);
	
	/**
	 * To save project entity
	 * @param entity
	 */
	public void saveProjectEntity(ProjectEntity entity);
	
	/**
	 * To save group entity
	 * @param entity
	 */
	public int saveGroupEntity(GroupEntity entity);
	
	/**
	 * To save group member entity
	 * @param entity
	 */
	public void saveGroupMemberEntity(List<GroupMemberEntity> entity);
	
	/**
	 * To get applicant by idPk
	 * @param applicantIdPk
	 * @return ApplicantEntity
	 */
	public ApplicantEntity getApplicantByIdPk(int applicantIdPk);
	
	/**
	 * To save rejected applicant entity
	 * @param entity
	 */
	public void saveRejectedApplicantEntity(RejectedApplicantEntity entity);
	
	/**
	 * To get the details of the applicant
	 * @param entity
	 */
	public List<ApplicantDetailsEntity> getApplicantDetailsByIdPk(int applicantIdPk);
	
	/**
	 * To update the status of the applicant
	 * @param status
	 * @param idpks
	 */
	public void updateApplicantStatus(int status, List<Integer> idPks);
	
	/**
	 * To get all applicant by status
	 * @return List<JoinApplicantProject>
	 */
	public List<JoinApplicantProject> getAllApplicantByStatus(List<Integer> status);
	
	/**
	 * TO save the evaluated applicant
	 * @param entity
	 */
	public void saveEvaluateedApplicant(EvaluatedApplicantEntity entity);
}
