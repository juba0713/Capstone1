package capstone.model.logic;

import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.ProjectEntity;

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
	public void saveGroupEntity(GroupEntity entity);
	
	/**
	 * To save group member entity
	 * @param entity
	 */
	public void saveGroupMemberEntity(GroupMemberEntity entity);
}
