package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.TbiBoardInOutDto;
import capstone.model.logic.ApplicantLogic;
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

}
