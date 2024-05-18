package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.RejectedApplicantEntity;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantObj;
import capstone.model.service.CommonService;
import capstone.model.service.EmailService;
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

	@Override
	public OfficerInOutDto getAllApplicants() {
		
		OfficerInOutDto outDto = new OfficerInOutDto();
		
		List<Integer> status = List.of(0);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> listOfAppObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
			
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
			
			obj.setDescription(app.getDescription());
			
			obj.setConsent(app.getConsent());
			
			obj.setUniversity(app.getUniversity());
			
			obj.setTeam(commonService.convertToList(app.getTeam()));
			
			listOfAppObj.add(obj);
			
		}
		
		outDto.setListOfApplicants(listOfAppObj);
		
		return outDto;
	}
	

	@Override
	public OfficerInOutDto rejectApplicant(OfficerInOutDto inDto) throws MessagingException {
		
		OfficerInOutDto outDto = new OfficerInOutDto();
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
		
		ApplicantEntity applicantEntity = applicantLogic.getApplicantByIdPk(inDto.getApplicantIdPk());
		
		applicantLogic.updateApplicantStatus(2, List.of(inDto.getApplicantIdPk()));
				
		RejectedApplicantEntity rejectedApplicantEntity = new RejectedApplicantEntity();
		
		rejectedApplicantEntity.setApplicantIdPk(inDto.getApplicantIdPk());
		
		rejectedApplicantEntity.setFeedback(inDto.getFeedback());
		
		rejectedApplicantEntity.setResubmitFlg(inDto.getResubmitFlg());
		
		rejectedApplicantEntity.setCreateDate(timeNow);
		
		rejectedApplicantEntity.setDeleteFlg(false);
		
		applicantLogic.saveRejectedApplicantEntity(rejectedApplicantEntity);
		
		emailService.sendRejectionMail(inDto.getFeedback(), inDto.getResubmitFlg(), userLogic.getUserByIdPk(applicantEntity.getCreatedBy()).getEmail());
		
		return outDto;
	}

	@Override
	public OfficerInOutDto acceptApplicant(OfficerInOutDto inDto) {
		
		OfficerInOutDto outDto = new OfficerInOutDto();
			
		applicantLogic.updateApplicantStatus(1, List.of(inDto.getApplicantIdPk()));
		
		return outDto;
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
				
				applicantDetailsObj.setMethodology(app.getMethodology());
				
				applicantDetailsObj.setVitaeFile(app.getVitaeFile());
				
				applicantDetailsObj.setSupportLink(app.getSupportLink());
				
				applicantDetailsObj.setGroupName(app.getGroupName());
				
				applicantDetailsObj.setLeaderFirstName(app.getLeaderFirstName());
				
				applicantDetailsObj.setLeaderLastName(app.getLeaderLastName());
				
				applicantDetailsObj.setMobileNumber(app.getMobileNumber());
				
				applicantDetailsObj.setEmailAddress(app.getEmailAddress());
				
				applicantDetailsObj.setUniversity(app.getUniversity());
				
				
			}
			
			members[firstRow] = app.getMemberLastName()+", "+app.getMemberFirstName();
			
			firstRow++;
		}
		
		outDto.setApplicantDetailsObj(applicantDetailsObj);
		
		return outDto;
		
	}

}
