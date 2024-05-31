package capstone.model.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.ApplicantDao;
import capstone.model.dao.EvaluatedApplicantDao;
import capstone.model.dao.GroupDao;
import capstone.model.dao.GroupMemberDao;
import capstone.model.dao.ProjectDao;
import capstone.model.dao.RejectedApplicantDao;
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.RejectedApplicantEntity;
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
	
	@Autowired
	private RejectedApplicantDao rejectedApplicantDao;
	
	@Autowired
	private EvaluatedApplicantDao evaluatedApplicantDao;

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

	@Override
	public ApplicantEntity getApplicantByIdPk(int applicantIdPk) {
		
		return applicantDao.getApplicantByIdPk(applicantIdPk);
	}

	@Override
	public void saveRejectedApplicantEntity(RejectedApplicantEntity entity) {
	
		rejectedApplicantDao.save(entity);
	}


	@Override
	public List<ApplicantDetailsEntity> getApplicantDetailsByIdPk(int applicantIdPk) {
		
		return applicantDao.getApplicantDetailsByIdPk(applicantIdPk);
	}

	@Override
	public void updateApplicantStatus(int status, List<Integer> idPks) {
		
		applicantDao.updateApplicantStatus(status, idPks);
	}

	@Override
	public List<JoinApplicantProject> getAllApplicantByStatus(List<Integer> status) {
			
		return applicantDao.getAllApplicantByStatus(status);
	}

	@Override
	public void saveEvaluateedApplicant(EvaluatedApplicantEntity entity) {
		
		evaluatedApplicantDao.save(entity);
		
	}

	@Override
	public ApplicantEntity getApplicantByCreatedBy(int createdBy) {
		
		return applicantDao.getApplicantByCreatedBy(createdBy);
	}

	@Override
	public RejectedApplicantEntity getRejectedApplicantByToken(String token) {

		return rejectedApplicantDao.getRejectedApplicantByToken(token);
	}

	@Override
	public ProjectEntity getProjectByApplicantId(int applicantIdPk) {
		
		return projectDao.getProjectByApplicantId(applicantIdPk);
	}

	@Override
	public GroupEntity getGroupByApplicantId(int applicantIdPk) {

		return groupDao.getGroupByApplicantId(applicantIdPk);
	}

	@Override
	public void deleteAllPreviousMember(int groupIdPk) {
		
		groupMemberDao.deleteAllGroupMemberByGroupId(groupIdPk);
	}

	@Override
	public void updateApplicant(Boolean agreeFlg, int technologyAns, int productDevelopmentAns,
			int competitiveLandscapeAns, int productDesignAns, int teamAns, int goToMarketAns, int manufacturingAns,
			Boolean eligibilityAgreeFlg, Boolean commitmentOneFlg, Boolean commitmentTwoFlg, Boolean commitmentThreeFlg,
			Boolean commitmentFourFlg, int status, int idPk) {
		
		applicantDao.updateApplicant(agreeFlg, 
				technologyAns, 
				productDevelopmentAns, 
				competitiveLandscapeAns,
				productDesignAns, 
				teamAns, 
				goToMarketAns, 
				manufacturingAns, 
				eligibilityAgreeFlg, 
				commitmentOneFlg, 
				commitmentTwoFlg, 
				commitmentThreeFlg, 
				commitmentFourFlg, 
				status,
				idPk);
		
	}

	@Override
	public void updateProject(String projectTitle, 
			String projectDescription, 
			String[] teams,
			String problemStatement, 
			String targetMarket, 
			String solutionDescription, 
			String[] historicalTimeline,
			String[] productServiceOffering, 
			String[] pricingStrategy, 
			String intPropertyStatus,
			String objectives, 
			String scopeProposal, 
			String methodology, 
			String vitaeFile, 
			String supportLink,
			int applicantIdPk) {
		
			projectDao.updateProject(projectTitle, 
					projectDescription, 
					teams, 
					problemStatement, 
					targetMarket, 
					solutionDescription, 
					historicalTimeline, 
					productServiceOffering, 
					pricingStrategy, 
					intPropertyStatus, 
					objectives,
					scopeProposal, 
					methodology, 
					vitaeFile, 
					supportLink, 
					applicantIdPk);
	}

	@Override
	public void updateGroup(String groupName, 
			String firstName, 
			String lastName, 
			String mobileNumber, 
			String address,
			String university,
			int applicantIdPk) {
		
		groupDao.updateGroupByApplicantId(groupName, 
				firstName, 
				lastName, 
				mobileNumber, 
				address,
				university, 
				applicantIdPk);
		
	}

	@Override
	public void updateEvaluatedApplicant(String token, int applicantIdPk) {
		
		evaluatedApplicantDao.updateEvaluatedApplicant(token, applicantIdPk);
	}

	@Override
	public EvaluatedApplicantEntity getEvaluatedApplicantByToken(String token) {
		
		return evaluatedApplicantDao.getEvaluatedApplicantByToken(token);
	}

	@Override
	public void deleteApplicantByCreatedBy(int createdBy) {
		
		applicantDao.deleteApplicantByCreatedBy(createdBy);
		
	}

	@Override
	public void updateApplicantCeritificate(String certificateName, int applicantIdPk) {
		
		applicantDao.updateApplicantCertificate(certificateName, applicantIdPk);
	}
	
}
