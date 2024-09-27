package capstone.model.logic;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.AcceptedApplicantEntity;
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantDetailsFeedbackEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.ApplicantMonthly;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.EvaluationDetailsEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.ManagerEvaluatedApplicantEntity;
import capstone.model.dao.entity.ManagerEvaluationDetailsEntity;
import capstone.model.dao.entity.PrescreenDetailsEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.RejectedApplicantEntity;
import capstone.model.dao.entity.UserCertificateEntity;

@Service
public interface ApplicantLogic {
	// In the ApplicantLogic interface, abstraction is achieved by defining methods
	// for saving and retrieving various entities related to applicants, projects,
	// groups, and evaluations without exposing how these operations are
	// implemented.

	// An interface in OOP defines a contract that classes must adhere to,
	// specifying methods that need to be implemented by any class that implements
	// the interface. The ApplicantLogic interface specifies a set of methods
	// related to applicant management:
	/**
	 * To save applicant entity
	 * 
	 * @param entity
	 * @return int
	 */
	public int saveApplicantEntity(ApplicantEntity entity);

	/**
	 * To save project entity
	 * 
	 * @param entity
	 */
	public void saveProjectEntity(ProjectEntity entity);

	/**
	 * To save group entity
	 * 
	 * @param entity
	 */
	public int saveGroupEntity(GroupEntity entity);

	/**
	 * To save group member entity
	 * 
	 * @param entity
	 */
	public void saveGroupMemberEntity(List<GroupMemberEntity> entity);

	/**
	 * To get applicant by idPk
	 * 
	 * @param applicantIdPk
	 * @return ApplicantEntity
	 */
	public ApplicantEntity getApplicantByIdPk(int applicantIdPk);

	/**
	 * To save rejected applicant entity
	 * 
	 * @param entity
	 */
	public int saveRejectedApplicantEntity(RejectedApplicantEntity entity);

	/**
	 * To get the details of the applicant
	 * 
	 * @param entity
	 */
	public List<ApplicantDetailsEntity> getApplicantDetailsByIdPk(int applicantIdPk);

	/**
	 * To update the status of the applicant
	 * 
	 * @param status
	 * @param idpks
	 */
	public void updateApplicantStatus(int status, List<Integer> idPks);

	/**
	 * To get all applicant by status
	 * 
	 * @return List<JoinApplicantProject>
	 */
	public List<JoinApplicantProject> getAllApplicantByStatus(List<Integer> status);

	/**
	 * TO save the evaluated applicant
	 * 
	 * @param entity
	 */
	public int saveEvaluateedApplicant(EvaluatedApplicantEntity entity);

	/**
	 * TO get applicant by who created it
	 * 
	 * @param entity
	 */
	public ApplicantEntity getApplicantByCreatedBy(int createdBy);

	/**
	 * To get rejetced applicant by token
	 * 
	 * @param token
	 * @return RejectedApplicantEntity
	 */
	public RejectedApplicantEntity getRejectedApplicantByToken(String token);
	
	/**
	 * To get rejetced applicant by ID
	 * 
	 * @param idPk
	 * @return RejectedApplicantEntity
	 */
	public RejectedApplicantEntity getRejectedApplicantById(int idPk);
	
	/**
	 * To get project by applicant id
	 * @param applicantIdPk
	 * @return ProjectEntity
	 */
	public ProjectEntity getProjectByApplicantId(int applicantIdPk);
	
	/**
	 * To get group by applicant id
	 * @param applicantIdPk
	 * @return ProjectEntity
	 */
	public GroupEntity getGroupByApplicantId(int applicantIdPk);
	
	/**
	 * TO delete all previous group member
	 * @param groupIdPk
	 */
	public void deleteAllPreviousMember(int groupIdPk);
	
	/**
	 * To update applicant by id 
	 * @param agreeFlg
	 * @param technologyAns
	 * @param productDevelopmentAns
	 * @param competitiveLandscapeAns
	 * @param productDesignAns
	 * @param teamAns
	 * @param goToMarketAns
	 * @param manufacturingAns
	 * @param eligibilityAgreeFlg
	 * @param commitmentOneFlg
	 * @param commitmentTwoFlg
	 * @param commitmentThreeFlg
	 * @param commitmentFourFlg
	 * @param idPk
	 */
	public void updateApplicant(Boolean agreeFlg,
			int technologyAns,
			int productDevelopmentAns,
			int competitiveLandscapeAns,
			int productDesignAns,
			int teamAns,
			int goToMarketAns,
			int manufacturingAns,
			Boolean eligibilityAgreeFlg,
			Boolean commitmentOneFlg,
			Boolean commitmentTwoFlg,
			Boolean commitmentThreeFlg,
			Boolean commitmentFourFlg,
			int status,
			int idPk
			);
	
	/**
	 * To update project
	 * @param projectTitle
	 * @param projectDescription
	 * @param teams
	 * @param problemStatement
	 * @param targetMarket
	 * @param solutionDescription
	 * @param historicalTimeline
	 * @param productServiceOffering
	 * @param pricingStrategy
	 * @param intPropertyStatus
	 * @param objectives
	 * @param scopeProposal
	 * @param methodology
	 * @param vitaeFile
	 * @param supportLink
	 * @param applicantIdPk
	 */
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
			int applicantIdPk);
	
	/**
	 * To update group
	 * @param groupName
	 * @param firstName
	 * @param lastName
	 * @param mobileNumber
	 * @param address
	 * @param university
	 * @param applicantIdPk
	 */
	public void updateGroup(String groupName,
			String firstName,
			String lastName,
			String mobileNumber,
			String address,
			String university,
			int applicantIdPk);
	
	/**
	 * To update evaluate applicant table
	 * @param token
	 * @param applicantIdPk
	 */
	public void updateEvaluatedApplicant(String token, boolean resubmitFlg, int applicantIdPk);
	
	/**
	 * To get evaluated applicant by token
	 * 
	 * @param token
	 * @return EvaluatedApplicantEntity
	 */
	public EvaluatedApplicantEntity getEvaluatedApplicantByToken(String token);
	
	/**
	 * To delete an applicant by created by
	 * @param createdBy
	 */
	public void deleteApplicantByCreatedBy(int createdBy);
	
	/**
	 * to update applicant certificate
	 * @param certificateName
	 * @param applicantIdPk
	 */
	public void updateApplicantCeritificate(String certificateName, int applicantIdPk);
	
	/**
	 * To save the accepted applicant
	 * @param entity
	 */
	public int saveAcceptedApplicantEntity(AcceptedApplicantEntity entity);
	
	public void updatePreviousAcceptedApplicant(int applicantIdPk);
	
	public void updatePreviousRejectedApplicant(int applicantIdPk);
	
	public void updatePreviousEvaluatedApplicant(int applicantIdPk);
	
	public void savePrescreenDetailsEntity(PrescreenDetailsEntity entity);
	
	public void saveEvaluationDetailsEntity(EvaluationDetailsEntity entity);
	
	public List<ApplicantMonthly> getApplicantOnTodayMonth();
	
	public List<ApplicantMonthly> getApplicantRankingOnTodayMonth();
	
	public List<ApplicantMonthly> getApplicantRankingByYearMonth(int month, int year);
	
	public List<ApplicantDetailsFeedbackEntity> getApplicantDetailsWithFeedback(int idPk);
	
	public UserCertificateEntity getUserInformationForCeritificate(int applicantIdPk);
	
	public int saveManagerEvaluatedApplicant(ManagerEvaluatedApplicantEntity entity);
	
	public void saveManagerEvaluationDetails(ManagerEvaluationDetailsEntity entity);
	
	public PrescreenDetailsEntity getRejectedPrescreenDetailsByToken(String token);
	
	public PrescreenDetailsEntity getAcceptedPrescreenDetailsByApplicantIdPk(int applicantIdPk);
	
	public EvaluationDetailsEntity getEvaluationDetailsByToken(String token);
	
	public EvaluatedApplicantEntity getEvaluatedApplicantById(int idPk);
	
	public AcceptedApplicantEntity getAcceptedApplicantByApplicantIdPk(int applicantIdPk);
}
