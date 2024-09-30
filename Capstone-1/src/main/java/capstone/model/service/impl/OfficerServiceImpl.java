package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.AcceptedApplicantEntity;
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.OfficerDashboardEntity;
import capstone.model.dao.entity.PrescreenDetailsEntity;
import capstone.model.dao.entity.RejectedApplicantEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.OfficerDashboardObj;
import capstone.model.service.CommonService;
import capstone.model.service.EmailService;
import capstone.model.service.LoggedInUserService;
import capstone.model.service.OfficerService;
import jakarta.mail.MessagingException;

@Service
public class OfficerServiceImpl implements OfficerService{
	
	@Autowired
	private ApplicantLogic applicantLogic;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private LoggedInUserService loggedInUserService;

	@Override
	public OfficerInOutDto getAllApplicants()  throws Exception{
		
		OfficerInOutDto outDto = new OfficerInOutDto();
		
		List<Integer> status = List.of(0);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
				
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			//obj.setApplicantIdPk(app.getApplicantIdPk());
			
			String encrypted = commonService.encrypt(String.valueOf(app.getApplicantIdPk()));
			
			obj.setEncryptedApplicantIdPk(encrypted);
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
				
			obj.setUniversity(app.getUniversity());
			
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}
	

	@Override
	public void rejectApplicant(OfficerInOutDto inDto) throws MessagingException {
		
		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();
		       
        String token = "R"+UUID.randomUUID().toString().replace("-", "");
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
		
		ApplicantEntity applicantEntity = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		applicantLogic.updateApplicantStatus(2, List.of(inDto.getApplicantIdPk()));
		
		applicantLogic.updatePreviousRejectedApplicant(inDto.getApplicantIdPk());
		
		applicantLogic.updatePreviousAcceptedApplicant(inDto.getApplicantIdPk());
		
		RejectedApplicantEntity rejectedApplicantEntity = new RejectedApplicantEntity();
		
		rejectedApplicantEntity.setApplicantIdPk(inDto.getApplicantIdPk());
		
		rejectedApplicantEntity.setResubmitFlg(inDto.getResubmitFlg());
		
		rejectedApplicantEntity.setCreatedDate(timeNow);
		
		rejectedApplicantEntity.setDeleteFlg(false);
		
		rejectedApplicantEntity.setToken(token);
		
		rejectedApplicantEntity.setCreatedBy(loggedInUser.getIdPk());
		
		int idPk = applicantLogic.saveRejectedApplicantEntity(rejectedApplicantEntity);
		
		PrescreenDetailsEntity prescreen = new PrescreenDetailsEntity();
		
		prescreen.setAcceptedApplicantIdPk(0);
		
		prescreen.setRejectedApplicantIdPk(idPk);
		
		prescreen.setCtOneFlg(inDto.getCtOneFlg());
		
		prescreen.setCtOneComments(inDto.getCtOneComments());
		
		prescreen.setCtTwoFlg(inDto.getCtTwoFlg());
		
		prescreen.setCtTwoComments(inDto.getCtTwoComments());
		
		prescreen.setCtThreeFlg(inDto.getCtThreeFlg());
		
		prescreen.setCtThreeComments(inDto.getCtThreeComments());
		
		prescreen.setCtFourFlg(inDto.getCtFourFlg());
		
		prescreen.setCtFourComments(inDto.getCtFourComments());
		
		prescreen.setCtFiveFlg(inDto.getCtFiveFlg());
		
		prescreen.setCtFiveComments(inDto.getCtFiveComments());
		
		prescreen.setCtSixFlg(inDto.getCtSixFlg());
		
		prescreen.setCtSixComments(inDto.getCtSixComments());
		
		prescreen.setCtSevenFlg(inDto.getCtSevenFlg());
		
		prescreen.setCtSevenComments(inDto.getCtSevenComments());
		
		prescreen.setCtEightFlg(inDto.getCtEightFlg());
		
		prescreen.setCtEightComments(inDto.getCtEightComments());
		
		prescreen.setCtNineFlg(inDto.getCtNineFlg());
		
		prescreen.setCtNineComments(inDto.getCtNineComments());
		
		prescreen.setRecommendation(inDto.getRecommendation());
		
		prescreen.setCreatedBy(loggedInUser.getIdPk());
		
		prescreen.setCreatedDate(timeNow);
		
		prescreen.setDeleteFlg(false);
		
		applicantLogic.savePrescreenDetailsEntity(prescreen);
		
		emailService.sendRejectionMail(inDto.getFeedback(), 
															inDto.getResubmitFlg(), 
															userLogic.getUserByIdPk(applicantEntity.getCreatedBy()).getEmail(),
															token);

	}

	@Override
	public void acceptApplicant(OfficerInOutDto inDto) throws MessagingException {
		
		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
			
		ApplicantEntity applicantEntity = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		UserInfoAccountEntity account = userLogic.getUserAccountByUserIdPk(applicantEntity.getCreatedBy());
		
		AcceptedApplicantEntity acceptedEntity = new AcceptedApplicantEntity();
		
		acceptedEntity.setApplicantIdPk(inDto.getApplicantIdPk());
		
		acceptedEntity.setCreatedDate(timeNow);
		
		acceptedEntity.setCreatedBy(loggedInUser.getIdPk());
		
		acceptedEntity.setDeleteFlg(false);
		
		int idPk = applicantLogic.saveAcceptedApplicantEntity(acceptedEntity);
		
		PrescreenDetailsEntity prescreen = new PrescreenDetailsEntity();
		
		prescreen.setAcceptedApplicantIdPk(idPk);
		
		prescreen.setRejectedApplicantIdPk(0);
		
		prescreen.setCtOneFlg(inDto.getCtOneFlg());
		
		prescreen.setCtOneComments(inDto.getCtOneComments());
		
		prescreen.setCtTwoFlg(inDto.getCtTwoFlg());
		
		prescreen.setCtTwoComments(inDto.getCtTwoComments());
		
		prescreen.setCtThreeFlg(inDto.getCtThreeFlg());
		
		prescreen.setCtThreeComments(inDto.getCtThreeComments());
		
		prescreen.setCtFourFlg(inDto.getCtFourFlg());
		
		prescreen.setCtFourComments(inDto.getCtFourComments());
		
		prescreen.setCtFiveFlg(inDto.getCtFiveFlg());
		
		prescreen.setCtFiveComments(inDto.getCtFiveComments());
		
		prescreen.setCtSixFlg(inDto.getCtSixFlg());
		
		prescreen.setCtSixComments(inDto.getCtSixComments());
		
		prescreen.setCtSevenFlg(inDto.getCtSevenFlg());
		
		prescreen.setCtSevenComments(inDto.getCtSevenComments());
		
		prescreen.setCtEightFlg(inDto.getCtEightFlg());
		
		prescreen.setCtEightComments(inDto.getCtEightComments());
		
		prescreen.setCtNineFlg(inDto.getCtNineFlg());
		
		prescreen.setCtNineComments(inDto.getCtNineComments());
		
		prescreen.setRecommendation(inDto.getRecommendation());
		
		prescreen.setCreatedBy(loggedInUser.getIdPk());
		
		prescreen.setCreatedDate(timeNow);
		
		prescreen.setDeleteFlg(false);
		
		applicantLogic.savePrescreenDetailsEntity(prescreen);
		
		if(account.getPassword() == null) {
			applicantLogic.updateApplicantStatus(1, List.of(inDto.getApplicantIdPk()));
		}else {
			applicantLogic.updateApplicantStatus(3, List.of(inDto.getApplicantIdPk()));
		}
		
		emailService.sendAcceptedMail(userLogic.getUserByIdPk(applicantEntity.getCreatedBy()).getEmail());
		
	}


	@Override
	public OfficerInOutDto getApplicantDetails(OfficerInOutDto inDto) {
		
		OfficerInOutDto outDto = new OfficerInOutDto();
		
		List<ApplicantDetailsEntity> applicant = applicantLogic.getApplicantDetailsByIdPk(inDto.getApplicantIdPk());
		
		ApplicantDetailsObj applicantDetailsObj = new ApplicantDetailsObj();
		
		String[] members = new String[4];
		
		int firstRow = 0;
		for(ApplicantDetailsEntity app : applicant) {
			
			if(firstRow == 0) {
				
				applicantDetailsObj.setApplicantIdPk(app.getApplicantIdPk());
				
				applicantDetailsObj.setEmail(app.getEmail());
				
				applicantDetailsObj.setAgreeFlg(app.getAgreeFlg());
				
				applicantDetailsObj.setProjectTitle(app.getProjectTitle());
				
				applicantDetailsObj.setProjectDescription(app.getProjectDescription());		
				
				
				List<String[]> teams = new ArrayList<>();
							
				for(int i = 0; i < app.getTeams().length; i++) {
					teams.add(app.getTeams()[i].split("\\|"));
				}
				
				applicantDetailsObj.setTeams(teams);
				
				applicantDetailsObj.setProblemStatement(app.getProblemStatement());
				
				applicantDetailsObj.setTargetMarket(app.getTargetMarket());
				
				applicantDetailsObj.setSolutionDescription(app.getSolutionDescription());
				
				List<String[]> historicallTimelines = new ArrayList<>();
							
				for(int i = 0; i < app.getHistoricalTimeline().length; i++) {
					historicallTimelines.add(app.getHistoricalTimeline()[i].split("\\|"));
				}
				
				applicantDetailsObj.setHistoricalTimeline(historicallTimelines);
				
				applicantDetailsObj.setProductServiceOffering(app.getProductServiceOffering());
				
				applicantDetailsObj.setPricingStrategy(app.getPricingStrategy());
				
				applicantDetailsObj.setIntPropertyStatus(app.getIntPropertyStatus());
				
				applicantDetailsObj.setObjectives(app.getObjectives());
				
				applicantDetailsObj.setScopeProposal(app.getScopeProposal());
				
				applicantDetailsObj.setMethodology(app.getMethodology());
				
				applicantDetailsObj.setVitaeFile(app.getVitaeFile());
				
				applicantDetailsObj.setSupportLink(app.getSupportLink());
				
				applicantDetailsObj.setGroupName(app.getGroupName());
				
				applicantDetailsObj.setLeaderFirstName(app.getLeaderFirstName());
				
				applicantDetailsObj.setLeaderLastName(app.getLeaderLastName());
				
				applicantDetailsObj.setMobileNumber(app.getMobileNumber());
				
				applicantDetailsObj.setAddress(app.getAddress());
				
				applicantDetailsObj.setUniversity(app.getUniversity());
				
				applicantDetailsObj.setTechnologyAns(app.getTechnologyAns());

				applicantDetailsObj.setProductDesignAns(app.getProductDesignAns());
				
				applicantDetailsObj.setCompetitiveLandscapeAns(app.getCompetitiveLandscapeAns());
				
				applicantDetailsObj.setProductDevelopmentAns(app.getProductDevelopmentAns());
				
				applicantDetailsObj.setTeamAns(app.getTeamAns());
				
				applicantDetailsObj.setGoToMarketAns(app.getGoToMarketAns());
				
				applicantDetailsObj.setManufacturingAns(app.getManufacturingAns());
				
				applicantDetailsObj.setEligibilityAgreeFlg(app.getEligibilityAgreeFlg());
				
				applicantDetailsObj.setCommitmentOneFlg(app.getCommitmentOneFlg());
				
				applicantDetailsObj.setCommitmentTwoFlg(app.getCommitmentTwoFlg());
				
				applicantDetailsObj.setCommitmentThreeFlg(app.getCommitmentThreeFlg());
				
				applicantDetailsObj.setCommitmentFourFlg(app.getCommitmentFourFlg());
				
				applicantDetailsObj.setStatus(app.getStatus());
				
			}

			members[firstRow] = app.getMemberLastName()+", "+app.getMemberFirstName();
			
			firstRow++;
		}
		
		applicantDetailsObj.setMembers(members);
		
		outDto.setApplicantDetailsObj(applicantDetailsObj);
		
		return outDto;
		
	}


	@Override
	public OfficerInOutDto getDetailsForOfficerDashboard() {
		
		OfficerInOutDto outDto = new OfficerInOutDto();
		
		OfficerDashboardEntity entity = userLogic.getDetailsForOfficerDashboard();
		
		OfficerDashboardObj obj = new OfficerDashboardObj();
		
		obj.setPendingApplicationCount(entity.getPendingApplicationCount());
		
		obj.setAcceptedApplicationCount(entity.getAcceptedApplicationCount());
		
		obj.setRejectedApplicationEligibleCount(entity.getRejectedApplicationEligibleCount());
		
		obj.setRejectedApplicationNotEligibleCount(entity.getRejectedApplicationNotEligibleCount());
		
		obj.setResubmittedApplicationCount(entity.getResubmittedApplicationCount());
		
		outDto.setOfficerDashboardObj(obj);
		
		return outDto;
	}

}
