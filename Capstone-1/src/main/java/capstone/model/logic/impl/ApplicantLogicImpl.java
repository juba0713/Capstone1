package capstone.model.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.ApplicantDao;
import capstone.model.dao.GroupDao;
import capstone.model.dao.GroupMemberDao;
import capstone.model.dao.ProjectDao;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.logic.ApplicantLogic;

@Service
public class ApplicantLogicImpl implements ApplicantLogic{

	@Autowired
	private ApplicantDao applicantDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private GroupMemberDao groupMemberDao;

	@Override
	public int saveApplicantEntity(ApplicantEntity entity) {
		
		applicantDao.save(entity);
		
		return entity.getIdPk();
	}

	@Override
	public void saveProjectEntity(ProjectEntity entity) {
		
		projectDao.save(entity);
		
	}

	@Override
	public int saveGroupEntity(GroupEntity entity) {
		
		groupDao.save(entity);
		
		return entity.getIdPk();
	}

	@Override
	public void saveGroupMemberEntity(List<GroupMemberEntity> entities) {
		
		groupMemberDao.saveAll(entities);
	}
	

}
