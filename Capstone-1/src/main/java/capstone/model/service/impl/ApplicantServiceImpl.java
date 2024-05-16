package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ErrorObj;
import capstone.model.service.ApplicantService;
import capstone.model.service.CommonService;
import capstone.model.service.LoggedInUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Autowired
	private LoggedInUserService loggedInUserService;
	
	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CommonService commonService;
	
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
		
		String[] leaderNames = commonService.splitArray(inDto.getGroupLeader());
		
		UserInformationEntity newUser = new UserInformationEntity();
		
		newUser.setEmail(inDto.getEmail());
		
		newUser.setFirstName(leaderNames[0]);
		
		newUser.setLastName(leaderNames[1]);
		
		newUser.setMobileNumber(inDto.getLeaderNumber());	
		
		newUser.setRole("APPLICANT");
		
		newUser.setInitialChangePass(false);
		
		newUser.setCreatedDate(currentDateTime);
		
		newUser.setDeleteFlg(false);
		
		int userIdPk = userLogic.saveUser(newUser);
		
		UserInfoAccountEntity newUserAccount = new UserInfoAccountEntity();
		
		newUserAccount.setUserIdPk(userIdPk);
		
		newUserAccount.setCreatedDate(currentDateTime);
		
		newUserAccount.setDeleteFlg(false);
		
		userLogic.saveUserAccount(newUserAccount);
		
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
		
		applicantEntity.setStatus(0);
		
		applicantEntity.setCreatedBy(userIdPk);
		
		int applicantIdPk = applicantLogic.saveApplicantEntity(applicantEntity);
		
		ProjectEntity projectEntity = new ProjectEntity();
		
		projectEntity.setApplicantIdPk(applicantIdPk);
		
		projectEntity.setProjectTitle(inDto.getProjectTitle());
		
		projectEntity.setProjectDescription(inDto.getProjectDescription());
		
		projectEntity.setTeams(commonService.convertToArray(inDto.getTeams()));
		
		projectEntity.setProblemStatement(inDto.getProblemStatement());
		
		projectEntity.setTargetMarket(inDto.getTargetMarket());
		
		projectEntity.setSolutionDescription(inDto.getSolutionDescription());
		
		projectEntity.setHistoricalTimeline(commonService.convertToArray(inDto.getHistoricalTimeline()));
		
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
				
				String[] memberNames = commonService.splitArray(member);
				
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
	
	@Override
	public void changePassword(ApplicantInOutDto inDto) {
	
		UserInformationEntity user = loggedInUserService.getUserInformation();
		
		UserInfoAccountEntity userAcc = loggedInUserService.getUserInfoAccount(user.getIdPk());
		
		user.setInitialChangePass(true);
		
		userLogic.saveUser(user);
		
		userAcc.setPassword(encoder.encode(inDto.getNewPassword()));
		
		userLogic.saveUserAccount(userAcc);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("initialChangePass", user.getInitialChangePass());

	}
	
	@Override
	public ApplicantInOutDto validatePassword(ApplicantInOutDto inDto) {
		
		UserInfoAccountEntity userAcc = loggedInUserService.getUserInfoAccount(loggedInUserService.getUserInformation().getIdPk());
		
		ApplicantInOutDto result = new ApplicantInOutDto();
		
		ErrorObj error = new ErrorObj();
		
		List<String> currentPasswordError = new ArrayList<>();
		
		List<String> newPasswordError = new ArrayList<>();
		
		List<String> confirmPasswordError = new ArrayList<>();
		
		if(inDto.getCurrentPassword().isEmpty() || inDto.getCurrentPassword() == null) {
			
			currentPasswordError.add(MessageConstant.CURRENT_PASSWORD_ERROR);
					
			result.setResult(CommonConstant.INVALID);
		}
		
		if(inDto.getNewPassword().isEmpty() || inDto.getNewPassword() == null) {
			
			newPasswordError.add(MessageConstant.NEW_PASSWORD_ERROR);
			
			result.setResult(CommonConstant.INVALID);
		}
		
		if(encoder.matches(inDto.getNewPassword(), userAcc.getPassword())) {
			
			newPasswordError.add(MessageConstant.NEW_INITIAL_EQUAL_ERROR);
			
			result.setResult(CommonConstant.INVALID);
		}
		
		if(!inDto.getNewPassword().equals(inDto.getConfirmPassword())) {
			
			newPasswordError.add(MessageConstant.NEW_CONFIRM_EQUAL_ERROR);

			result.setResult(CommonConstant.INVALID);

		}
		
		if(inDto.getConfirmPassword().isEmpty() || inDto.getConfirmPassword() == null) {
			
			confirmPasswordError.add(MessageConstant.CONFIRM_PASSWORD_ERROR);
			
			result.setResult(CommonConstant.INVALID);
		}
		
		if(CommonConstant.INVALID.equals(result.getResult())) {
			
			error.setNewPasswordError(newPasswordError);
			
			error.setConfirmPasswordError(confirmPasswordError);
			
			error.setCurrentPasswordError(currentPasswordError);
			
			result.setError(error);
		}else {
			result.setResult(CommonConstant.VALID);
		}
		
		return result;
	}


}