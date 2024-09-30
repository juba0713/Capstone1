package capstone.model.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

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
import capstone.model.dao.entity.ApplicantDetailsFeedbackEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.ApplicantMonthly;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.EvaluationDetailsEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.ManagerDashboardEntity;
import capstone.model.dao.entity.ManagerEvaluatedApplicantEntity;
import capstone.model.dao.entity.ManagerEvaluationDetailsEntity;
import capstone.model.dao.entity.UserCertificateEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantMonthlyObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
import capstone.model.object.ManagerDashboardObj;
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
	
	@Autowired
	private LoggedInUserService loggedInUserService;

	@Override
	public ManagerInOutDto getAllApplicants() throws Exception{
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<Integer> status = List.of(0,00,1,2,3,4,40,5,50,6,7,8);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));;
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setTotalRating(app.getTotalRating());
			
			obj.setUniversity(app.getUniversity());
			
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
	public void updateApplicantStatus(ManagerInOutDto inDto) throws MessagingException {
		
		applicantLogic.updateApplicantStatus(inDto.getStatus(), inDto.getChosenApplicant()); 
		
		if(inDto.getTransferring()) {
			
			List<UserInformationEntity> users = userLogic.getUsersByApplicantIdPks(inDto.getChosenApplicant());
			
			for(UserInformationEntity user : users) {
				emailService.sendTBITransferedMail(user.getEmail());		
			
			}
		}
		
	}

	@Override
	public ManagerInOutDto getAllEvaluatedApplicants() throws Exception {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		 
		List<Integer> status = List.of(5,50,6,7);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			//obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
			
			obj.setEmail(app.getEmail());
			
			obj.setTotalRating(app.getTotalRating());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
					
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getAllAcceptedApplicants() throws Exception {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<Integer> status = List.of(1,3);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			//obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
			
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
			
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
	public ManagerInOutDto sendResubmissionMail(ManagerInOutDto inDto) throws MessagingException {
		
		String token = "F"+UUID.randomUUID().toString().replace("-", "");
		
		ApplicantEntity applicant = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		UserInformationEntity user = userLogic.getUserByIdPk(applicant.getCreatedBy());
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		boolean resubmitFlg = false;
		
		if(inDto.getStatus() == 6) {
			applicantLogic.updateApplicantStatus(inDto.getStatus(), List.of(inDto.getApplicantIdPk()));
			emailService.sendFailedMail(true,user.getEmail(), token);
			resubmitFlg = true;
		}else {
			applicantLogic.updateApplicantStatus(inDto.getStatus(), List.of(inDto.getApplicantIdPk()));
			emailService.sendFailedMail(false,user.getEmail(), token);
		}
		
		applicantLogic.updateEvaluatedApplicant(token,resubmitFlg,  inDto.getApplicantIdPk());
		
		applicantLogic.updatePreviousEvaluatedApplicant(inDto.getApplicantIdPk());
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getAppllicantOnTodayMonth() throws Exception {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<ApplicantMonthly> applicants = applicantLogic.getApplicantOnTodayMonth();
		
		List<ApplicantMonthlyObj> applicantMonthlyObj = new ArrayList<>();
		
		for(ApplicantMonthly app : applicants) {
			ApplicantMonthlyObj obj = new ApplicantMonthlyObj();
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
			
			obj.setProjectTitle(app.getProjectTitle());
			
			applicantMonthlyObj.add(obj);
		}
		
		outDto.setApplicantMonthlyObj(applicantMonthlyObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getApplicantDetailsWithFeedback(ManagerInOutDto inDto) throws Exception {
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<ApplicantDetailsFeedbackEntity> applicant = applicantLogic.getApplicantDetailsWithFeedback(inDto.getApplicantIdPk());
		
		ApplicantDetailsObj applicantDetailsObj = new ApplicantDetailsObj();
		
		ApplicantOfficerFeedbackObj appOffFeedbackObj = new ApplicantOfficerFeedbackObj();
		
		ApplicantTbiFeedbackObj appTbiFeedbackObj = new ApplicantTbiFeedbackObj();
		
		String[] members = new String[4];
		
		int firstRow = 0;
		for(ApplicantDetailsFeedbackEntity app : applicant) {
			
			if(firstRow == 0) {
				
				//applicantDetailsObj.setApplicantIdPk(app.getApplicantIdPk());
				
				applicantDetailsObj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
				
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
				
				appOffFeedbackObj.setCtOneFlg(app.getOCtOneFlg());
				
				appOffFeedbackObj.setCtOneComments(app.getOCtOneComments());
				
				appOffFeedbackObj.setCtTwoFlg(app.getOCtTwoFlg());
				
				appOffFeedbackObj.setCtTwoComments(app.getOCtTwoComments());
				
				appOffFeedbackObj.setCtThreeFlg(app.getOCtThreeFlg());
				
				appOffFeedbackObj.setCtThreeComments(app.getOCtThreeComments());
				
				appOffFeedbackObj.setCtFourFlg(app.getOCtFourFlg());
				
				appOffFeedbackObj.setCtFourComments(app.getOCtFourComments());
				
				appOffFeedbackObj.setCtFiveFlg(app.getOCtFiveFlg());
				
				appOffFeedbackObj.setCtFiveComments(app.getOCtFiveComments());
				
				appOffFeedbackObj.setCtSixFlg(app.getOCtSixFlg());
				
				appOffFeedbackObj.setCtSixComments(app.getOCtSixComments());
				
				appOffFeedbackObj.setCtSevenFlg(app.getOCtSevenFlg());
				
				appOffFeedbackObj.setCtSevenComments(app.getOCtSevenComments());
				
				appOffFeedbackObj.setCtEightFlg(app.getOCtEightFlg());
				
				appOffFeedbackObj.setCtEightComments(app.getOCtEightComments());
				
				appOffFeedbackObj.setCtNineFlg(app.getOCtNineFlg());
				
				appOffFeedbackObj.setCtNineComments(app.getOCtNineComments());
				
				appOffFeedbackObj.setRecommendation(app.getRecommendation());
				
				appTbiFeedbackObj.setCtOneRating(app.getTCtOneRating());
				
				appTbiFeedbackObj.setCtOneComments(app.getTCtOneComments());
				
				appTbiFeedbackObj.setCtTwoRating(app.getTCtTwoRating());
				
				appTbiFeedbackObj.setCtTwoComments(app.getTCtTwoComments());
				
				appTbiFeedbackObj.setCtThreeRating(app.getTCtThreeRating());
				
				appTbiFeedbackObj.setCtThreeComments(app.getTCtThreeComments());	
				
				appTbiFeedbackObj.setCtFourRating(app.getTCtFourRating());
				
				appTbiFeedbackObj.setCtFourComments(app.getTCtFourComments());
				
				appTbiFeedbackObj.setCtFiveRating(app.getTCtFiveRating());
				
				appTbiFeedbackObj.setCtFiveComments(app.getTCtFiveComments());
				
				appTbiFeedbackObj.setCtSixRating(app.getTCtSixRating());
				
				appTbiFeedbackObj.setCtSixComments(app.getTCtSixComments());
				
				appTbiFeedbackObj.setCtSevenRating(app.getTCtSevenRating());
				
				appTbiFeedbackObj.setCtSevenComments(app.getTCtSevenComments());
				
				appTbiFeedbackObj.setCtEightRating(app.getTCtEightRating());
				
				appTbiFeedbackObj.setCtEightComments(app.getTCtEightComments());
				
				appTbiFeedbackObj.setTbiFeedback(app.getTbiFeedback());
				
				
			
			}

			members[firstRow] = app.getMemberLastName()+", "+app.getMemberFirstName();
			
			firstRow++;
		}
		
		applicantDetailsObj.setMembers(members);
		
		outDto.setApplicantDetailsObj(applicantDetailsObj);
		
		outDto.setApplicantOffFeedbackObj(appOffFeedbackObj);
		
		outDto.setApplicantTbiFeedbackObj(appTbiFeedbackObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto issuedCertificate(ManagerInOutDto inDto) throws MessagingException {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		UserCertificateEntity userCertificate = applicantLogic.getUserInformationForCeritificate(inDto.getApplicantIdPk());
		
		if (userCertificate.getTotalRating() >= 60) {

			String folderPath = env.getProperty("certificate.path").toString();

			try {
				// Load the image
				File imageFile = new File(folderPath + "base_certificate.png");
				BufferedImage image = ImageIO.read(imageFile);

				Graphics g = image.getGraphics();

				g.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 130));
				g.setColor(new Color(253, 204, 1));

				String fullName = userCertificate.getFirstName() + " " + userCertificate.getLastName();
				int x = 132;
				int y = 760;
				g.drawString(fullName, x, y);

				g.dispose();

				String fileName = "certificate_" + userCertificate.getUserIdPk();
				File outputFile = new File(folderPath + fileName + ".png");
				ImageIO.write(image, "png", outputFile);

				applicantLogic.updateApplicantCeritificate(fileName, inDto.getApplicantIdPk());
				
				emailService.sendIssuedCertificate(userCertificate.getEmail());
				
			} catch (IOException e) {
				
				outDto.setResult(CommonConstant.INVALID);
				
				return outDto;
			}
		}
		
		outDto.setResult(CommonConstant.VALID);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto evaluateApplicant(ManagerInOutDto inDto) throws MessagingException {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();

		Timestamp timeNow = new Timestamp(System.currentTimeMillis());

		applicantLogic.updateApplicantStatus(8, List.of(inDto.getApplicantIdPk()));

		ManagerEvaluatedApplicantEntity evaluatedApplicantEntity = new ManagerEvaluatedApplicantEntity();

		evaluatedApplicantEntity.setApplicantIdPk(inDto.getApplicantIdPk());

		evaluatedApplicantEntity.setCreatedBy(loggedInUser.getIdPk());

		evaluatedApplicantEntity.setCreatedDate(timeNow);

		evaluatedApplicantEntity.setDeleteFlg(false);
		
		UserInformationEntity user = userLogic.getUserByApplicantIdPk(inDto.getApplicantIdPk());
		
		int idPk = applicantLogic.saveManagerEvaluatedApplicant(evaluatedApplicantEntity);

		ManagerEvaluationDetailsEntity evaluation = new ManagerEvaluationDetailsEntity();
		
		evaluation.setEvaluatedApplicantIdPk(idPk);
		
		evaluation.setCtOneRating(inDto.getCtOneRating());
		
		evaluation.setCtOneComments(inDto.getCtOneComments());
		
		evaluation.setCtTwoRating(inDto.getCtTwoRating());
		
		evaluation.setCtTwoComments(inDto.getCtTwoComments());
		
		evaluation.setCtThreeRating(inDto.getCtThreeRating());
		
		evaluation.setCtThreeComments(inDto.getCtThreeComments());
		
		evaluation.setCtFourRating(inDto.getCtFourRating());
		
		evaluation.setCtFourComments(inDto.getCtFourComments());
		
		evaluation.setCtFiveRating(inDto.getCtFiveRating());
		
		evaluation.setCtFiveComments(inDto.getCtFiveComments());
		
		evaluation.setCtSixRating(inDto.getCtSixRating());
		
		evaluation.setCtSixComments(inDto.getCtSixComments());
		
		evaluation.setCtSevenRating(inDto.getCtSevenRating());
		
		evaluation.setCtSevenComments(inDto.getCtSevenComments());
		
		evaluation.setCtEightRating(inDto.getCtEightRating());
		
		evaluation.setCtEightComments(inDto.getCtEightComments());
		
		evaluation.setManagerFeedback(inDto.getManagerFeedback());
		
		evaluation.setCreatedBy(loggedInUser.getIdPk());

		evaluation.setCreatedDate(timeNow);

		evaluation.setDeleteFlg(false);
		
		evaluation.setTotal(commonService.calculateTotalRatings(inDto.getCtOneRating(),
				inDto.getCtTwoRating(),
				inDto.getCtThreeRating(),		
				inDto.getCtFourRating(),
				inDto.getCtFiveRating(),
				inDto.getCtSixRating(),
				inDto.getCtSevenRating(),
				inDto.getCtEightRating()));
				
		applicantLogic.saveManagerEvaluationDetails(evaluation);
		
		emailService.sendEvaluatedMailManager(user.getEmail());
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getAppllicantRankingOnTodayMonth() throws Exception {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<ApplicantMonthly> applicants = applicantLogic.getApplicantRankingOnTodayMonth();
		
		List<ApplicantMonthlyObj> applicantMonthlyObj = new ArrayList<>();
		
		for(ApplicantMonthly app : applicants) {
			ApplicantMonthlyObj obj = new ApplicantMonthlyObj();
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
			
			obj.setProjectTitle(app.getProjectTitle());
			
			applicantMonthlyObj.add(obj);
		}
		
		outDto.setApplicantRankingMonthlyObj(applicantMonthlyObj);
		
		return outDto;
	}

	@Override
	public ManagerInOutDto getAppllicantRankingByYearMonth(ManagerInOutDto inDto) throws Exception {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<ApplicantMonthly> applicants = applicantLogic.getApplicantRankingByYearMonth(inDto.getMonth(), inDto.getYear());
		
		System.out.println(applicants);
		
		List<ApplicantMonthlyObj> applicantMonthlyObj = new ArrayList<>();
		
		for(ApplicantMonthly app : applicants) {
			ApplicantMonthlyObj obj = new ApplicantMonthlyObj();
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));
			
			obj.setProjectTitle(app.getProjectTitle());
			
			applicantMonthlyObj.add(obj);
			
		}
		
		outDto.setApplicantRankingMonthlyObj(applicantMonthlyObj);
		
		return outDto;
		
	}

	@Override
	public ManagerInOutDto getDetailsForManagerDashboard() {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		ManagerDashboardEntity entity = userLogic.getDetailsForManagerDashboard();
		
		ManagerDashboardObj obj = new ManagerDashboardObj();
		
		obj.setTotalApplicationsCount(entity.getTotalApplicationsCount());
		
		obj.setInOfficerCount(entity.getInOfficerCount());
		
		obj.setInTbiboardCount(entity.getInTbiboardCount());
		
		obj.setIssuedCeritifcatesCount(entity.getIssuedCeritifcatesCount());
		
		obj.setPassedApplicationsCount(entity.getPassedApplicationsCount());
		
		obj.setFailedApplicationsCount(entity.getFailedApplicationsCount());
		
		obj.setAcceptedApplicationsCount(entity.getAcceptedApplicationsCount());
		
		obj.setEvalutedApplicaitonsCount(entity.getEvalutedApplicaitonsCount());
		
		obj.setAcceptanceRate(entity.getAcceptanceRate());
		
		obj.setRejectionRate(entity.getRejectionRate());
		
		obj.setResubmittedApplicationsCount(entity.getResubmittedApplicationsCount());
		
		obj.setRejectedApplicationEligibleCount(entity.getRejectedApplicationEligibleCount());
		
		obj.setRejectedApplicationNotEligibleCount(entity.getRejectedApplicationNotEligibleCount());
		
		outDto.setManagerDashboardObj(obj);
		
		return outDto;
	}
	
	
	
}
