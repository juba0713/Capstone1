package capstone.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.service.CommonService;
import capstone.model.service.EmailService;
import capstone.model.service.LoggedInUserService;
import capstone.model.service.ManagerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Environment env;

	@Override
	public ManagerInOutDto getAllApplicants() {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<Integer> status = List.of(0,1,2,3,4,5,6,7);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
			
			obj.setScore(app.getScore());
			
			obj.setFeedback(app.getFeedback());
			
			obj.setAcceptedBy(app.getAcceptedBy());
			
			obj.setEvaluatedBy(app.getEvaluatedBy());
			
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
		
	}

	@Override
	public ManagerInOutDto activateApplicantAccount(ManagerInOutDto inDto) throws MessagingException{
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		String randomPassword = commonService.generateRandomPass();
		
		ApplicantEntity applicantEntity = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		applicantLogic.updateApplicantStatus(inDto.getStatus(), List.of(inDto.getApplicantIdPk())); 
		
		emailService.sendActivationMail(randomPassword, userLogic.getUserByIdPk(applicantEntity.getCreatedBy()).getEmail());
		
		UserInfoAccountEntity userAccount = userLogic.getUserAccountByUserIdPk(applicantEntity.getCreatedBy());
		
		userAccount.setPassword(encoder.encode(randomPassword));
		
		userLogic.saveUserAccount(userAccount);
	       
		return outDto;
	}

	@Override
	public void updateApplicantStatus(ManagerInOutDto inDto) {
		
		applicantLogic.updateApplicantStatus(inDto.getStatus(), inDto.getChosenApplicant()); 

	}

	@Override
	public ManagerInOutDto getAllEvaluatedApplicants() {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<Integer> status = List.of(5,6,7);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
			
			obj.setScore(app.getScore());
			
			obj.setFeedback(app.getFeedback());
					
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getAllAcceptedApplicants() {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<Integer> status = List.of(1,3);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
			
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
			
			obj.setScore(app.getScore());
			
			obj.setFeedback(app.getFeedback());
			
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getApplicantDetails(ManagerInOutDto inDto) {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
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
				
				teams.add(app.getTeams()[0].split("\\|"));
				teams.add(app.getTeams()[1].split("\\|"));
				teams.add(app.getTeams()[2].split("\\|"));	
				
				applicantDetailsObj.setTeams(teams);
				
				applicantDetailsObj.setProblemStatement(app.getProblemStatement());
				
				applicantDetailsObj.setTargetMarket(app.getTargetMarket());
				
				applicantDetailsObj.setSolutionDescription(app.getSolutionDescription());
				
				List<String[]> historicallTimelines = new ArrayList<>();
				
				historicallTimelines.add(app.getHistoricalTimeline()[0].split("\\|"));
				historicallTimelines.add(app.getHistoricalTimeline()[1].split("\\|"));
				historicallTimelines.add(app.getHistoricalTimeline()[2].split("\\|"));
				historicallTimelines.add(app.getHistoricalTimeline()[3].split("\\|"));
				historicallTimelines.add(app.getHistoricalTimeline()[4].split("\\|"));
				
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
				
				applicantDetailsObj.setScore(app.getScore());
				
				applicantDetailsObj.setFeedback(app.getFeedback());
			}

			members[firstRow] = app.getMemberLastName()+", "+app.getMemberFirstName();
			
			firstRow++;
		}
		
		applicantDetailsObj.setMembers(members);
		
		outDto.setApplicantDetailsObj(applicantDetailsObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto sendResubmissionMail(ManagerInOutDto inDto) throws MessagingException {
		
		String token = "F"+UUID.randomUUID().toString().replace("-", "");
		
		ApplicantEntity applicant = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		UserInformationEntity user = userLogic.getUserByIdPk(applicant.getCreatedBy());
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		if(inDto.getStatus() == 6) {
			applicantLogic.updateApplicantStatus(inDto.getStatus(), List.of(inDto.getApplicantIdPk()));
			emailService.sendFailedMail(true,user.getEmail(), token);
		}else {
			applicantLogic.updateApplicantStatus(inDto.getStatus(), List.of(inDto.getApplicantIdPk()));
			emailService.sendFailedMail(false,user.getEmail(), token);
		}
		
		applicantLogic.updateEvaluatedApplicant(token, inDto.getApplicantIdPk());
		
		return outDto;
	}
	
}
