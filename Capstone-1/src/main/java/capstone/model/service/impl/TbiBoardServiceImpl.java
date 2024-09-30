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

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.EvaluationDetailsEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.TbiBoardDashboardEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.TbiBoardDashboardObj;
import capstone.model.service.CommonService;
import capstone.model.service.EmailService;
import capstone.model.service.LoggedInUserService;
import capstone.model.service.TbiBoardService;
import jakarta.mail.MessagingException;

@Service
public class TbiBoardServiceImpl implements TbiBoardService {

	@Autowired
	private ApplicantLogic applicantLogic;

	@Autowired
	private EmailService emailService;

	@Autowired
	private LoggedInUserService loggedInUserService;
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private UserLogic userLogic;


	@Override
	public TbiBoardInOutDto getAllApplicants() throws Exception {
		TbiBoardInOutDto outDto = new TbiBoardInOutDto();

		List<Integer> status = List.of(4);

		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);

		List<ApplicantObj> listOfAppObj = new ArrayList<>();

		for (JoinApplicantProject app : listOfApplicant) {

			ApplicantObj obj = new ApplicantObj();
			
			obj.setEncryptedApplicantIdPk(commonService.encrypt(String.valueOf(app.getApplicantIdPk())));;

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
	public void evaluateApplicant(TbiBoardInOutDto inDto) throws MessagingException {

		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();

		Timestamp timeNow = new Timestamp(System.currentTimeMillis());

		applicantLogic.updateApplicantStatus(5, List.of(inDto.getApplicantIdPk()));

		EvaluatedApplicantEntity evaluatedApplicantEntity = new EvaluatedApplicantEntity();

		evaluatedApplicantEntity.setApplicantIdPk(inDto.getApplicantIdPk());

		evaluatedApplicantEntity.setCreatedBy(loggedInUser.getIdPk());

		evaluatedApplicantEntity.setCreatedDate(timeNow);

		evaluatedApplicantEntity.setDeleteFlg(false);
		
		UserInformationEntity user = userLogic.getUserByApplicantIdPk(inDto.getApplicantIdPk());
		
		applicantLogic.updatePreviousEvaluatedApplicant(inDto.getApplicantIdPk());

		int idPk = applicantLogic.saveEvaluateedApplicant(evaluatedApplicantEntity);

		EvaluationDetailsEntity evaluation = new EvaluationDetailsEntity();
		
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
		
		evaluation.setTbiFeedback(inDto.getTbiFeedback());
		
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
				
		applicantLogic.saveEvaluationDetailsEntity(evaluation);
		
		emailService.sendEvaluatedMail(user.getEmail());

	}

	@Override
	public TbiBoardInOutDto getApplicantDetails(TbiBoardInOutDto inDto) throws Exception {

		TbiBoardInOutDto outDto = new TbiBoardInOutDto();

		List<ApplicantDetailsEntity> applicant = applicantLogic.getApplicantDetailsByIdPk(inDto.getApplicantIdPk());

		ApplicantDetailsObj applicantDetailsObj = new ApplicantDetailsObj();

		String[] members = new String[4];

		int firstRow = 0;
		for (ApplicantDetailsEntity app : applicant) {

			if (firstRow == 0) {

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

			}

			members[firstRow] = app.getMemberLastName() + ", " + app.getMemberFirstName();

			firstRow++;
		}

		applicantDetailsObj.setMembers(members);

		outDto.setApplicantDetailsObj(applicantDetailsObj);

		return outDto;
	}

	@Override
	public TbiBoardInOutDto getDetailsForTbiBoardDashboard() {
		
		TbiBoardInOutDto outDto = new TbiBoardInOutDto();
		
		TbiBoardDashboardEntity entity = userLogic.getDetailsForTbiBoardDashboard();
		
		TbiBoardDashboardObj obj = new TbiBoardDashboardObj();
		
		obj.setPendingApplicantCount(entity.getPendingApplicantCount());
		
		obj.setEvaluatedApplicantCount(entity.getEvaluatedApplicantCount());
		
		obj.setAcceptanceRate(entity.getAcceptanceRate());
		
		obj.setFailedRate(entity.getFailedRate());
		
		outDto.setTbiBoardDashboardObj(obj);
		
		return outDto;
	}

}
