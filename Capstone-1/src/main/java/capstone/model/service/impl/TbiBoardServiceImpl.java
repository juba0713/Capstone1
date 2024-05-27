package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.service.CommonService;
import capstone.model.service.LoggedInUserService;
import capstone.model.service.TbiBoardService;

@Service
public class TbiBoardServiceImpl implements TbiBoardService {
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LoggedInUserService loggedInUserService;

	@Override
	public TbiBoardInOutDto getAllApplicants() {
		TbiBoardInOutDto outDto = new TbiBoardInOutDto();
		
		List<Integer> status = List.of(4);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
			
			obj.setDescription(app.getDescription());
			
			obj.setConsent(app.getConsent());
			
			obj.setTeam(commonService.convertToList(app.getTeam()));
			
			obj.setStatus(app.getStatus());
			
			obj.setUniversity(app.getUniversity());
						
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}

	@Override
	public void evaluateApplicant(TbiBoardInOutDto inDto) {
		
		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
		
		applicantLogic.updateApplicantStatus(5, List.of(inDto.getApplicantIdPk()));
		
		EvaluatedApplicantEntity evaluatedApplicantEntity = new EvaluatedApplicantEntity();
		
		evaluatedApplicantEntity.setApplicantIdPk(inDto.getApplicantIdPk());
		
		evaluatedApplicantEntity.setScore(inDto.getScore());
		
		evaluatedApplicantEntity.setFeedback(inDto.getFeedback());
		
		evaluatedApplicantEntity.setCreatedBy(loggedInUser.getIdPk());
		
		evaluatedApplicantEntity.setCreatedDate(timeNow);
		
		evaluatedApplicantEntity.setDeleteFlg(false);
		
		applicantLogic.saveEvaluateedApplicant(evaluatedApplicantEntity);

	}

	@Override
	public TbiBoardInOutDto getApplicantDetails(TbiBoardInOutDto inDto) {
		
		TbiBoardInOutDto outDto = new TbiBoardInOutDto();
		
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
				
				teams.add(app.getTeams()[0].split("-"));
				teams.add(app.getTeams()[1].split("-"));
				teams.add(app.getTeams()[2].split("-"));	
				
				applicantDetailsObj.setTeams(teams);
				
				applicantDetailsObj.setProblemStatement(app.getProblemStatement());
				
				applicantDetailsObj.setTargetMarket(app.getTargetMarket());
				
				applicantDetailsObj.setSolutionDescription(app.getSolutionDescription());
				
				List<String[]> historicallTimelines = new ArrayList<>();
				
				historicallTimelines.add(app.getHistoricalTimeline()[0].split("-"));
				historicallTimelines.add(app.getHistoricalTimeline()[1].split("-"));
				historicallTimelines.add(app.getHistoricalTimeline()[2].split("-"));
				historicallTimelines.add(app.getHistoricalTimeline()[3].split("-"));
				historicallTimelines.add(app.getHistoricalTimeline()[4].split("-"));
				
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

}
