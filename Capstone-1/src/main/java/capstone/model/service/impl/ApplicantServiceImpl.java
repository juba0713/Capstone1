package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Override
	public ApplicantInOutDto validateApplicant(ApplicantInOutDto inDto) {
		
		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		outDto.setResult(CommonConstant.VALID);
		
		return outDto;
	}

	@Override
	public ApplicantInOutDto saveApplicant(ApplicantInOutDto inDto) {
		
		Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
		
		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		ApplicantEntity applicantEntity = new ApplicantEntity();
		
		applicantEntity.setEmail(inDto.getEmail());
		
		applicantEntity.setAgreeFlg(inDto.getAgreeFlg());
		
		applicantEntity.setTechnologyAns(inDto.getTechonologyAns());
		
		applicantEntity.setProductDevelopmentAns(inDto.getProductDevelopmentAns());
		
		applicantEntity.setCompetitiveLandscapeAns(inDto.getCompetitiveLandscapeAns());
		
		applicantEntity.setProductDesignAns(inDto.getProductDesignAns());
		
		applicantEntity.setTeamAns(inDto.getTeamAns());
		
		applicantEntity.setGoToMarketAns(inDto.getGoToMarketAns());
		
		applicantEntity.setManufacturingAns(inDto.getManufacturingAns());
		
		applicantEntity.setEligibilityAgreeFlg(inDto.getEligibilityAgreeFlg());
		
		applicantEntity.setCommitmentOneFlg(inDto.getCommitmentOneFlg());
		
		applicantEntity.setCommitmentTwoFlg(inDto.getCommitmentTwoFlg());
		
		applicantEntity.setCommitmentThreeFlg(inDto.getCommitmentThreeFlg());
		
		applicantEntity.setCommitmentFourFlg(inDto.getCommitmentFourFlg());
		
		applicantEntity.setCreatedDate(currentDateTime);
		
		applicantEntity.setDeleteFlg(false);
		
		int applicantIdPk = applicantLogic.saveApplicantEntity(applicantEntity);
		
		ProjectEntity projectEntity = new ProjectEntity();
		
		projectEntity.setApplicantIdPk(applicantIdPk);
		
		projectEntity.setProjectTitle(inDto.getProjectTitle());
		
		projectEntity.setProjectDescription(inDto.getProjectDescription());
		
		projectEntity.setTeams(convertToArray(inDto.getTeams()));
		
		projectEntity.setProblemStatement(inDto.getProblemStatement());
		
		projectEntity.setTargetMarket(inDto.getTargetMarket());
		
		projectEntity.setSolutionDescription(inDto.getSolutionDescription());
		
		projectEntity.setHistoricalTimeline(convertToArray(inDto.getHistoricalTimeline()));
		
		projectEntity.setProductServiceOffering(inDto.getProductServiceOffering());
		
		projectEntity.setPricingStrategy(inDto.getPricingStrategy());
		
		projectEntity.setIntPropertyStatus(inDto.getIntPropertyStatus());
		
		projectEntity.setObjectives(inDto.getObjectives());
		
		projectEntity.setScopeProposal(inDto.getScopeProposal());
		
		projectEntity.setMethodology(inDto.getMethodology());
		
		projectEntity.setVitaeFile("FIXED");
		
		projectEntity.setSupportLink(inDto.getSupportLink());
		
		projectEntity.setCreatedDate(currentDateTime);
		
		projectEntity.setDeleteFlg(false);
		
		applicantLogic.saveProjectEntity(projectEntity);
		
		GroupEntity groupEntity = new GroupEntity();
		
		groupEntity.setApplicantIdPk(applicantIdPk);
		
		groupEntity.setGroupName(inDto.getGroupName());
		
		String[] leaderNames = splitArray(inDto.getGroupLeader());
		
		groupEntity.setFirstName(leaderNames[0]);
		
		groupEntity.setLastName(leaderNames[1]);
		
		groupEntity.setMobileNumber(inDto.getLeaderNumber());
		
		groupEntity.setEmailAddress(inDto.getEmail());
		
		groupEntity.setUniversity(inDto.getUniversity());
		
		groupEntity.setCreatedDate(currentDateTime);
		
		groupEntity.setDeleteFlg(false);
		
		int groupIdPk = applicantLogic.saveGroupEntity(groupEntity);
		
		List<GroupMemberEntity> members = new ArrayList<>();
		
		for(String member : inDto.getMembers()) {
			
			if(member != null) {
				
				GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
				
				groupMemberEntity.setGroupIdPk(groupIdPk);
				
				String[] memberNames = splitArray(inDto.getGroupLeader());
				
				groupMemberEntity.setFirstName(memberNames[0]);
				
				groupMemberEntity.setLastName(memberNames[1]);
				
				groupMemberEntity.setCreatedDate(currentDateTime);
				
				groupMemberEntity.setDeleteFlg(false);
				
				members.add(groupMemberEntity);
			}
		}
		
		applicantLogic.saveGroupMemberEntity(members);
		
		return outDto;
	}
	
	public List<String> convertToArray(List<String[]> var) {
		
		List<String> convertedValue = new ArrayList<>();
		
		if(var != null && !var.isEmpty()) {
			
			for(String[] value : var) {
				
				String stringValue = String.join(" ", value);
				
				convertedValue.add(stringValue);
			}	
		}
			
		return convertedValue;
	}
	
	public String[] splitArray(String var) {
		
		return var.split(",");
	}


}