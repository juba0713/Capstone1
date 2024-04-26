package capstone.model.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantEntity;
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
	 * To get all applicant
	 */
	public List<JoinApplicantProject> getAllApplicant();
	
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
}
