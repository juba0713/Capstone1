package capstone.model.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.model.dao.entity.AcceptedApplicantEntity;
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.EvaluatedApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.PrescreenDetailsEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.RejectedApplicantEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dao.entity.EvaluationDetailsEntity;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
import capstone.model.object.ApplicantOfficerFeedbackObj;
import capstone.model.object.ApplicantTbiFeedbackObj;
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

	@Autowired
	private Environment env;

	@Override
	public ApplicantInOutDto validateApplication(ApplicantInOutDto inDto) {

		ApplicantInOutDto outDto = new ApplicantInOutDto();

		ErrorObj errorObj = new ErrorObj();

		List<String> emailError = new ArrayList<>();
		List<String> projectTitleError = new ArrayList<>();
		List<String> projectDescriptionError = new ArrayList<>();
		List<String> teamsError = new ArrayList<>();
		List<String> problemStatementError = new ArrayList<>();
		List<String> targetMarketError = new ArrayList<>();
		List<String> solutionDecriptionError = new ArrayList<>();
		List<String> historicalTimelineError = new ArrayList<>();
		List<String> productServiceOfferingError = new ArrayList<>();
		List<String> objectivesError = new ArrayList<>();
		List<String> scopeProposalError = new ArrayList<>();
		List<String> methodologyError = new ArrayList<>();
		List<String> groupNameError = new ArrayList<>();
		List<String> groupLeaderError = new ArrayList<>();
		List<String> leaderNumberError = new ArrayList<>();
		List<String> leaderAddressError = new ArrayList<>();
		List<String> membersError = new ArrayList<>();
		List<String> vitaeFileError = new ArrayList<>();
		String agreeFlgError = CommonConstant.BLANK;
		String technologyAnsError = CommonConstant.BLANK;
		String productDevelopmentAnsError = CommonConstant.BLANK;
		String CompetitiveLandscapeAnsError = CommonConstant.BLANK;
		String productDesignAnsError = CommonConstant.BLANK;
		String teamAnsError = CommonConstant.BLANK;
		String goToMarketAnsError = CommonConstant.BLANK;
		String manufacturingAnsError = CommonConstant.BLANK;
		String eligibilityAgreeFlgError = CommonConstant.BLANK;
		String commitmentOneFlgError = CommonConstant.BLANK;
		String commitmentTwoFlgError = CommonConstant.BLANK;
		String commitmentThreeFlgError = CommonConstant.BLANK;
		String commitmentFourFlgError = CommonConstant.BLANK;

		boolean hasError = false;

		if (CommonConstant.BLANK.equals(inDto.getEmail())) {
			emailError.add(MessageConstant.EMAIL_BLANK);
			hasError = true;
		}
		if (!CommonConstant.EMAIL_PATTERN.matcher(inDto.getEmail()).matches()) {
			emailError.add(MessageConstant.EMAIL_INCORRECT_FORMAT);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getProjectTitle())) {
			projectTitleError.add(MessageConstant.PROJECT_TITLE_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getProjectDescription())) {
			projectDescriptionError.add(MessageConstant.PROJECT_DESCRIPTION_BLANK);
			hasError = true;
		}

		for (String[] team : inDto.getTeams()) {
			if (team[0].length() == 0 || team[1].length() == 0) {
				teamsError.add(MessageConstant.TEAM_BLANK);
				hasError = true;
				break;
			}
		}

		if (CommonConstant.BLANK.equals(inDto.getProblemStatement())) {
			problemStatementError.add(MessageConstant.PROBLEM_STATEMENT_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getTargetMarket())) {
			targetMarketError.add(MessageConstant.TARGET_MARKET_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getSolutionDescription())) {
			solutionDecriptionError.add(MessageConstant.SOLUTION_DESCRIPTION_BLANK);
			hasError = true;
		}

		for (String[] time : inDto.getHistoricalTimeline()) {
			if (!time[0].isEmpty() && time[1].isEmpty() || time[0].isEmpty() && !time[1].isEmpty()) {
				historicalTimelineError.add(MessageConstant.HISTORICAL_TIMELINE_ERROR);
				hasError = true;
				break;
			}
		}

		if (inDto.getProductServiceOffering().get(0).isEmpty() &&
				inDto.getProductServiceOffering().get(1).isEmpty() &&
				inDto.getProductServiceOffering().get(2).isEmpty() &&
				inDto.getProductServiceOffering().get(3).isEmpty() &&
				inDto.getPricingStrategy().get(0).isEmpty() &&
				inDto.getPricingStrategy().get(1).isEmpty() &&
				inDto.getPricingStrategy().get(2).isEmpty() &&
				inDto.getPricingStrategy().get(3).isEmpty()) {
			productServiceOfferingError.add(MessageConstant.PRODUCT_SERVICE_ERROR);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getObjectives())) {
			objectivesError.add(MessageConstant.OBJECTIVES_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getScopeProposal())) {
			scopeProposalError.add(MessageConstant.SCOPE_PROPOSAL_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getMethodology())) {
			methodologyError.add(MessageConstant.METHODOLOGY_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getGroupName())) {
			groupNameError.add(MessageConstant.GROUP_NAME_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getGroupLeader())) {
			groupLeaderError.add(MessageConstant.GROUP_LEADER_BLANK);
			hasError = true;
		}

		if (inDto.getGroupLeader().split(",").length != 2) {
			groupLeaderError.add(MessageConstant.NAME_INCORRECT_FORMAT);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getLeaderNumber())) {
			leaderNumberError.add(MessageConstant.MOBILE_NUMBER_BLANK);
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getLeaderAddress())) {
			leaderAddressError.add(MessageConstant.ADDRESS_BLANK);
			hasError = true;
		}

		for (String member : inDto.getMembers()) {
			if (member.split(",").length != 2 && !CommonConstant.BLANK.equals(member)) {
				membersError.add(MessageConstant.NAME_INCORRECT_FORMAT);
				hasError = true;
				break;
			}
		}

		if (inDto.getAgreeFlg() == null) {
			agreeFlgError = MessageConstant.AGREE_FLG_ERROR;
			hasError = true;
		}

		if (inDto.getTechnologyAns() == 0) {
			technologyAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getProductDevelopmentAns() == 0) {
			productDevelopmentAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getCompetitiveLandscapeAns() == 0) {
			CompetitiveLandscapeAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getProductDesignAns() == 0) {
			productDesignAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getTeamAns() == 0) {
			teamAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getGoToMarketAns() == 0) {
			goToMarketAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getManufacturingAns() == 0) {
			manufacturingAnsError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getEligibilityAgreeFlg() == null) {
			eligibilityAgreeFlgError = MessageConstant.ELIGIBLITY_FLG_ERROR;
			hasError = true;
		}

		if (inDto.getCommitmentOneFlg() == null) {
			commitmentOneFlgError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getCommitmentTwoFlg() == null) {
			commitmentTwoFlgError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getCommitmentThreeFlg() == null) {
			commitmentThreeFlgError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getCommitmentFourFlg() == null) {
			commitmentFourFlgError = MessageConstant.OPTION_ERROR;
			hasError = true;
		}

		if (inDto.getToken() == null && inDto.getReApplyToken() == null && inDto.getVitaeFile().isEmpty()) {
			vitaeFileError.add(MessageConstant.VITAE_FILE_BLANK);
			hasError = true;
		}

		String extension = FilenameUtils.getExtension(inDto.getVitaeFile().getOriginalFilename()).toLowerCase();
		if (inDto.getToken() == null &&  inDto.getReApplyToken() == null  && !extension.equals("pdf")) {
			vitaeFileError.add(MessageConstant.VITAE_FILE_FORMAT_ERROR);
			hasError = true;
		}

		if (hasError) {

			errorObj.setEmailError(emailError);

			errorObj.setProjectTitleError(projectTitleError);

			errorObj.setProjectDescriptionError(projectDescriptionError);

			errorObj.setTeamsError(teamsError);

			errorObj.setProblemStatementError(problemStatementError);

			errorObj.setTargetMarketError(targetMarketError);

			errorObj.setSolutionDescriptionError(solutionDecriptionError);

			errorObj.setHistoricalTimelineError(historicalTimelineError);

			errorObj.setProductServiceOfferingError(productServiceOfferingError);

			errorObj.setObjectivesError(objectivesError);

			errorObj.setScopeProposalError(scopeProposalError);

			errorObj.setMethodologyError(methodologyError);

			errorObj.setGroupNameError(groupNameError);

			errorObj.setGroupLeaderError(groupLeaderError);

			errorObj.setLeaderNumberError(leaderNumberError);

			errorObj.setLeaderAddressError(leaderAddressError);

			errorObj.setAgreeFlgError(agreeFlgError);

			errorObj.setTechnologyAnsError(technologyAnsError);

			errorObj.setProductDevelopmentAnsError(productDevelopmentAnsError);

			errorObj.setCompetitiveLandscapeAnsError(CompetitiveLandscapeAnsError);

			errorObj.setProductDesignAnsError(productDesignAnsError);

			errorObj.setTeamAnsError(teamAnsError);

			errorObj.setGoToMarketAnsError(goToMarketAnsError);

			errorObj.setManufacturingAnsError(manufacturingAnsError);

			errorObj.setEligibilityAgreeFlgError(eligibilityAgreeFlgError);

			errorObj.setCommitmentOneFlgError(commitmentOneFlgError);

			errorObj.setCommitmentTwoFlgError(commitmentTwoFlgError);

			errorObj.setCommitmentThreeFlgError(commitmentThreeFlgError);

			errorObj.setCommitmentFourFlgError(commitmentFourFlgError);

			errorObj.setMembersError(membersError);

			errorObj.setVitaeFileError(vitaeFileError);

			outDto.setError(errorObj);

			outDto.setResult(CommonConstant.INVALID);

			return outDto;
		}

		outDto.setResult(CommonConstant.VALID);

		return outDto;
	}

	@Override
	public ApplicantInOutDto saveApplication(ApplicantInOutDto inDto) throws IOException {

		Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());

		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		String[] leaderNames = commonService.splitArray(inDto.getGroupLeader());
		
		Path uploadPath = Paths.get(env.getProperty("file.path"));
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		int userIdPk = 0;
		
		//If token is not null means resubmission
		if(inDto.getToken() == null || inDto.getReApplyToken() != null) {
			
			if(inDto.getReApplyToken()==null || inDto.getReApplyToken().isEmpty()) {
				
				UserInformationEntity newUser = new UserInformationEntity();
				
				newUser.setEmail(inDto.getEmail());
		
				newUser.setFirstName(leaderNames[1]);
		
				newUser.setLastName(leaderNames[0]);
		
				newUser.setMobileNumber(inDto.getLeaderNumber());
		
				newUser.setRole("APPLICANT");
		
				newUser.setInitialChangePass(false);
		
				newUser.setCreatedDate(currentDateTime);
		
				newUser.setDeleteFlg(false);
				
				newUser.setUpdatedDate(currentDateTime);
				
				userIdPk = userLogic.saveUser(newUser);
		
				UserInfoAccountEntity newUserAccount = new UserInfoAccountEntity();
		
				newUserAccount.setUserIdPk(userIdPk);
		
				newUserAccount.setCreatedDate(currentDateTime);
		
				newUserAccount.setDeleteFlg(false);
		
				userLogic.saveUserAccount(newUserAccount);
			}else {
				
				if(inDto.getReApplyToken().charAt(0)=='F') {
					UserInformationEntity user = userLogic.getUserByEvaluatedToken(inDto.getReApplyToken());
					
					userIdPk = user.getIdPk();
				}else {
					UserInformationEntity user = userLogic.getUserByRejectedToken(inDto.getReApplyToken());
					
					userIdPk = user.getIdPk();
				}
				
				System.out.println(userIdPk);
				
				applicantLogic.deleteApplicantByCreatedBy(userIdPk);
			}

			ApplicantEntity applicantEntity = new ApplicantEntity();
	
			applicantEntity.setEmail(inDto.getEmail());
	
			applicantEntity.setAgreeFlg(inDto.getAgreeFlg());
	
			applicantEntity.setTechnologyAns(inDto.getTechnologyAns());
	
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

		   MultipartFile vitaeFile = inDto.getVitaeFile();
	
			int lastDotIndex = vitaeFile.getOriginalFilename().lastIndexOf('.');
	
			String fileName = vitaeFile.getOriginalFilename().substring(0, lastDotIndex) + "_" + userIdPk
					+ vitaeFile.getOriginalFilename().substring(lastDotIndex);
	
			Path filePath = uploadPath.resolve(fileName);
	
			Files.copy(vitaeFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	
			projectEntity.setVitaeFile(fileName);
	
			projectEntity.setSupportLink(inDto.getSupportLink());
	
			projectEntity.setCreatedDate(currentDateTime);
	
			projectEntity.setDeleteFlg(false);
	
			applicantLogic.saveProjectEntity(projectEntity);
	
			GroupEntity groupEntity = new GroupEntity();
	
			groupEntity.setApplicantIdPk(applicantIdPk);
	
			groupEntity.setGroupName(inDto.getGroupName());
	
			groupEntity.setFirstName(leaderNames[1]);
	
			groupEntity.setLastName(leaderNames[0]);
	
			groupEntity.setMobileNumber(inDto.getLeaderNumber());
	
			groupEntity.setAddress(inDto.getLeaderAddress());
	
			groupEntity.setUniversity(inDto.getUniversity());
	
			groupEntity.setCreatedDate(currentDateTime);
	
			groupEntity.setDeleteFlg(false);
	
			int groupIdPk = applicantLogic.saveGroupEntity(groupEntity);
	
			List<GroupMemberEntity> members = new ArrayList<>();
	
			for (String member : inDto.getMembers()) {
	
				if (!CommonConstant.BLANK.equals(member)) {
	
					GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
	
					groupMemberEntity.setGroupIdPk(groupIdPk);
	
					String[] memberNames = commonService.splitArray(member);
	
					groupMemberEntity.setFirstName(memberNames[1]);
	
					groupMemberEntity.setLastName(memberNames[0]);
	
					groupMemberEntity.setCreatedDate(currentDateTime);
	
					groupMemberEntity.setDeleteFlg(false);
	
					members.add(groupMemberEntity);
				}
			}
	
			applicantLogic.saveGroupMemberEntity(members);
		}else {
			
			int status = 0;
			
			int applicantIdPk = 0;
			
			if(inDto.getToken().charAt(0)=='F') {
				status = 4;
				
				EvaluatedApplicantEntity evaluatedApplicant = applicantLogic.getEvaluatedApplicantByToken(inDto.getToken());
				
				evaluatedApplicant.setIdPk(evaluatedApplicant.getIdPk());
				
				evaluatedApplicant.setDeleteFlg(true);
				
				applicantLogic.saveEvaluateedApplicant(evaluatedApplicant);
				
				applicantIdPk = evaluatedApplicant.getApplicantIdPk();
				
			}else {
				RejectedApplicantEntity rejectedApplicant = applicantLogic.getRejectedApplicantByToken(inDto.getToken());
					
				rejectedApplicant.setIdPk(rejectedApplicant.getIdPk());
				
				rejectedApplicant.setDeleteFlg(true);
				
				applicantLogic.saveRejectedApplicantEntity(rejectedApplicant);
				
				applicantIdPk = rejectedApplicant.getApplicantIdPk();
			}
			
			ApplicantEntity applicant = applicantLogic.getApplicantByIdPk(applicantIdPk);
			
			applicantLogic.updateApplicant(inDto.getAgreeFlg(),
					inDto.getTechnologyAns(), 
					inDto.getProductDevelopmentAns(), 
					inDto.getCompetitiveLandscapeAns(), 
					inDto.getProductDesignAns(), 
					inDto.getTeamAns(), 
					inDto.getGoToMarketAns(), 
					inDto.getManufacturingAns(), 
					inDto.getEligibilityAgreeFlg(), 
					inDto.getCommitmentOneFlg(),
					inDto.getCommitmentTwoFlg(),
					inDto.getCommitmentThreeFlg(),
					inDto.getCommitmentFourFlg(),
					status,
					applicant.getIdPk());
			
			String fileName = "";
			
			if(!inDto.getVitaeFile().isEmpty()) {
				MultipartFile vitaeFile = inDto.getVitaeFile();
				
				int lastDotIndex = vitaeFile.getOriginalFilename().lastIndexOf('.');
		
				fileName = vitaeFile.getOriginalFilename().substring(0, lastDotIndex) + "_" + applicant.getCreatedBy()
						+ vitaeFile.getOriginalFilename().substring(lastDotIndex);
		
				Path filePath = uploadPath.resolve(fileName);
		
				Files.copy(vitaeFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			}else {
				fileName = inDto.getVitaeFileName();
			}
			
			
			applicantLogic.updateProject(inDto.getProjectTitle(), 
					inDto.getProjectDescription(), 
					commonService.listToArray(commonService.convertToArray(inDto.getTeams())),
					inDto.getProblemStatement(), 
					inDto.getTargetMarket(), 
					inDto.getSolutionDescription(), 
					commonService.listToArray(commonService.convertToArray(inDto.getHistoricalTimeline())),
					commonService.listToArray(inDto.getProductServiceOffering()),
					commonService.listToArray(inDto.getPricingStrategy()),
					inDto.getIntPropertyStatus(), 
					inDto.getObjectives(), 
					inDto.getScopeProposal(), 
					inDto.getMethodology(), 
					fileName, 
					inDto.getSupportLink(), 
					applicant.getIdPk());
			
			GroupEntity group = applicantLogic.getGroupByApplicantId(applicant.getIdPk());
			
			applicantLogic.updateGroup(inDto.getGroupName(), 
					leaderNames[0], 
					leaderNames[1], 
					inDto.getLeaderNumber(), 
					inDto.getLeaderAddress(),
					inDto.getUniversity(),
					applicant.getIdPk());
			
			applicantLogic.deleteAllPreviousMember(group.getIdPk());
			
			List<GroupMemberEntity> members = new ArrayList<>();
			
			for (String member : inDto.getMembers()) {
	
				if (!CommonConstant.BLANK.equals(member)) {
	
					GroupMemberEntity groupMemberEntity = new GroupMemberEntity();
	
					groupMemberEntity.setGroupIdPk(group.getIdPk());
	
					String[] memberNames = commonService.splitArray(member);
	
					groupMemberEntity.setFirstName(memberNames[1]);
	
					groupMemberEntity.setLastName(memberNames[0]);
	
					groupMemberEntity.setCreatedDate(currentDateTime);
	
					groupMemberEntity.setDeleteFlg(false);
	
					members.add(groupMemberEntity);
				}
			}
	
			applicantLogic.saveGroupMemberEntity(members);
		}

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

		UserInfoAccountEntity userAcc = loggedInUserService
				.getUserInfoAccount(loggedInUserService.getUserInformation().getIdPk());

		ApplicantInOutDto result = new ApplicantInOutDto();

		ErrorObj error = new ErrorObj();

		List<String> currentPasswordError = new ArrayList<>();

		List<String> newPasswordError = new ArrayList<>();

		List<String> confirmPasswordError = new ArrayList<>();

		if (inDto.getCurrentPassword().isEmpty() || inDto.getCurrentPassword() == null) {

			currentPasswordError.add(MessageConstant.CURRENT_PASSWORD_ERROR);

			result.setResult(CommonConstant.INVALID);
		}

		if (inDto.getNewPassword().isEmpty() || inDto.getNewPassword() == null) {

			newPasswordError.add(MessageConstant.NEW_PASSWORD_ERROR);

			result.setResult(CommonConstant.INVALID);
		}

		if (encoder.matches(inDto.getNewPassword(), userAcc.getPassword())) {

			newPasswordError.add(MessageConstant.NEW_INITIAL_EQUAL_ERROR);

			result.setResult(CommonConstant.INVALID);
		}

		if (!inDto.getNewPassword().equals(inDto.getConfirmPassword())) {

			newPasswordError.add(MessageConstant.NEW_CONFIRM_EQUAL_ERROR);

			result.setResult(CommonConstant.INVALID);

		}

		if (inDto.getConfirmPassword().isEmpty() || inDto.getConfirmPassword() == null) {

			confirmPasswordError.add(MessageConstant.CONFIRM_PASSWORD_ERROR);

			result.setResult(CommonConstant.INVALID);
		}

		if (CommonConstant.INVALID.equals(result.getResult())) {

			error.setNewPasswordError(newPasswordError);

			error.setConfirmPasswordError(confirmPasswordError);

			error.setCurrentPasswordError(currentPasswordError);

			result.setError(error);
		} else {
			result.setResult(CommonConstant.VALID);
		}

		return result;
	}

	@Override
	public ApplicantInOutDto getApplicantDetails() {

		ApplicantInOutDto outDto = new ApplicantInOutDto();

		UserInformationEntity loggedInUser = loggedInUserService.getUserInformation();

		ApplicantEntity createdApplicant = applicantLogic.getApplicantByCreatedBy(loggedInUser.getIdPk());

		List<ApplicantDetailsEntity> applicant = applicantLogic.getApplicantDetailsByIdPk(createdApplicant.getIdPk());

		ApplicantDetailsObj applicantDetailsObj = new ApplicantDetailsObj();

		String[] members = new String[4];

		int firstRow = 0;
		for (ApplicantDetailsEntity app : applicant) {

			if (firstRow == 0) {

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
				
				applicantDetailsObj.setCertificateName(app.getCertificateName());		
				
				applicantDetailsObj.setTotalRating(app.getTotalRating());
				
			}

			members[firstRow] = app.getMemberLastName() + ", " + app.getMemberFirstName();
			
			firstRow++;
		}

		applicantDetailsObj.setMembers(members);

		outDto.setApplicantDetailsObj(applicantDetailsObj);

		return outDto;
	}

	@Override
	public ApplicantInOutDto getApplicantDetailsWithFeedbackByToken(ApplicantInOutDto inDto) {
		
		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		outDto.setOnlyOfficerFeedback(false);
		
		outDto.setBothFeedback(false);
		
		int applicantIdPk = 0;
		
		if(inDto.getToken().charAt(0)=='R') {
			
			PrescreenDetailsEntity rejectedPrescreen = applicantLogic.getRejectedPrescreenDetailsByToken(inDto.getToken());
			
			RejectedApplicantEntity rejectedApplicant = applicantLogic.getRejectedApplicantById(rejectedPrescreen.getRejectedApplicantIdPk());
			
			applicantIdPk = rejectedApplicant.getApplicantIdPk();
			
			ApplicantOfficerFeedbackObj appOffFeedbackObj = new ApplicantOfficerFeedbackObj();
			
			appOffFeedbackObj.setCtOneFlg(rejectedPrescreen.getCtOneFlg());
			
			appOffFeedbackObj.setCtOneComments(rejectedPrescreen.getCtOneComments());
			
			appOffFeedbackObj.setCtTwoFlg(rejectedPrescreen.getCtTwoFlg());
			
			appOffFeedbackObj.setCtTwoComments(rejectedPrescreen.getCtTwoComments());
			
			appOffFeedbackObj.setCtThreeFlg(rejectedPrescreen.getCtThreeFlg());
			
			appOffFeedbackObj.setCtThreeComments(rejectedPrescreen.getCtThreeComments());
			
			appOffFeedbackObj.setCtFourFlg(rejectedPrescreen.getCtFourFlg());
			
			appOffFeedbackObj.setCtFourComments(rejectedPrescreen.getCtFourComments());
			
			appOffFeedbackObj.setCtFiveFlg(rejectedPrescreen.getCtFiveFlg());
			
			appOffFeedbackObj.setCtFiveComments(rejectedPrescreen.getCtFiveComments());
			
			appOffFeedbackObj.setCtSixFlg(rejectedPrescreen.getCtSixFlg());
			
			appOffFeedbackObj.setCtSixComments(rejectedPrescreen.getCtSixComments());
			
			appOffFeedbackObj.setCtSevenFlg(rejectedPrescreen.getCtSevenFlg());
			
			appOffFeedbackObj.setCtSevenComments(rejectedPrescreen.getCtSevenComments());
			
			appOffFeedbackObj.setCtEightFlg(rejectedPrescreen.getCtEightFlg());
			
			appOffFeedbackObj.setCtEightComments(rejectedPrescreen.getCtEightComments());
			
			appOffFeedbackObj.setCtNineFlg(rejectedPrescreen.getCtNineFlg());
			
			appOffFeedbackObj.setCtNineComments(rejectedPrescreen.getCtNineComments());
			
			appOffFeedbackObj.setRecommendation(rejectedPrescreen.getRecommendation());
			
			outDto.setAppOffFeedbackObj(appOffFeedbackObj);
			
		}else if(inDto.getToken().charAt(0)=='F') {

			EvaluationDetailsEntity evaluationDetails = applicantLogic.getEvaluationDetailsByToken(inDto.getToken());
			
			EvaluatedApplicantEntity evaluatedApplicant = applicantLogic.getEvaluatedApplicantById(evaluationDetails.getIdPk());
			
			applicantIdPk = evaluatedApplicant.getApplicantIdPk();
			
			ApplicantTbiFeedbackObj appTbiFeedbackObj = new ApplicantTbiFeedbackObj();
			
			appTbiFeedbackObj.setCtOneRating(evaluationDetails.getCtOneRating());
			
			appTbiFeedbackObj.setCtOneComments(evaluationDetails.getCtOneComments());
			
			appTbiFeedbackObj.setCtTwoRating(evaluationDetails.getCtTwoRating());
			
			appTbiFeedbackObj.setCtTwoComments(evaluationDetails.getCtTwoComments());
			
			appTbiFeedbackObj.setCtThreeRating(evaluationDetails.getCtThreeRating());
			
			appTbiFeedbackObj.setCtThreeComments(evaluationDetails.getCtThreeComments());	
			
			appTbiFeedbackObj.setCtFourRating(evaluationDetails.getCtFourRating());
			
			appTbiFeedbackObj.setCtFourComments(evaluationDetails.getCtFourComments());
			
			appTbiFeedbackObj.setCtFiveRating(evaluationDetails.getCtFiveRating());
			
			appTbiFeedbackObj.setCtFiveComments(evaluationDetails.getCtFiveComments());
			
			appTbiFeedbackObj.setCtSixRating(evaluationDetails.getCtSixRating());
			
			appTbiFeedbackObj.setCtSixComments(evaluationDetails.getCtSixComments());
			
			appTbiFeedbackObj.setCtSevenRating(evaluationDetails.getCtSevenRating());
			
			appTbiFeedbackObj.setCtSevenComments(evaluationDetails.getCtSevenComments());
			
			appTbiFeedbackObj.setCtEightRating(evaluationDetails.getCtEightRating());
			
			appTbiFeedbackObj.setCtEightComments(evaluationDetails.getCtEightComments());
			
			appTbiFeedbackObj.setTbiFeedback(evaluationDetails.getTbiFeedback());
			
			AcceptedApplicantEntity acceptedApplicant = applicantLogic.getAcceptedApplicantByApplicantIdPk(applicantIdPk);
			
			PrescreenDetailsEntity rejectedPrescreen = applicantLogic.getAcceptedPrescreenDetailsByApplicantIdPk(acceptedApplicant.getIdPk());
				
			ApplicantOfficerFeedbackObj appOffFeedbackObj = new ApplicantOfficerFeedbackObj();
			
			appOffFeedbackObj.setCtOneFlg(rejectedPrescreen.getCtOneFlg());
			
			appOffFeedbackObj.setCtOneComments(rejectedPrescreen.getCtOneComments());
			
			appOffFeedbackObj.setCtTwoFlg(rejectedPrescreen.getCtTwoFlg());
			
			appOffFeedbackObj.setCtTwoComments(rejectedPrescreen.getCtTwoComments());
			
			appOffFeedbackObj.setCtThreeFlg(rejectedPrescreen.getCtThreeFlg());
			
			appOffFeedbackObj.setCtThreeComments(rejectedPrescreen.getCtThreeComments());
			
			appOffFeedbackObj.setCtFourFlg(rejectedPrescreen.getCtFourFlg());
			
			appOffFeedbackObj.setCtFourComments(rejectedPrescreen.getCtFourComments()); 
			
			appOffFeedbackObj.setCtFiveFlg(rejectedPrescreen.getCtFiveFlg());
			
			appOffFeedbackObj.setCtFiveComments(rejectedPrescreen.getCtFiveComments());
			
			appOffFeedbackObj.setCtSixFlg(rejectedPrescreen.getCtSixFlg());
			
			appOffFeedbackObj.setCtSixComments(rejectedPrescreen.getCtSixComments());
			
			appOffFeedbackObj.setCtSevenFlg(rejectedPrescreen.getCtSevenFlg());
			
			appOffFeedbackObj.setCtSevenComments(rejectedPrescreen.getCtSevenComments());
			
			appOffFeedbackObj.setCtEightFlg(rejectedPrescreen.getCtEightFlg());
			
			appOffFeedbackObj.setCtEightComments(rejectedPrescreen.getCtEightComments());
			
			appOffFeedbackObj.setCtNineFlg(rejectedPrescreen.getCtNineFlg());
			
			appOffFeedbackObj.setCtNineComments(rejectedPrescreen.getCtNineComments());
			
			appOffFeedbackObj.setRecommendation(rejectedPrescreen.getRecommendation());
			
			outDto.setAppOffFeedbackObj(appOffFeedbackObj);
			
			outDto.setApplicantTbiFeedbackObj(appTbiFeedbackObj);
			
			outDto.setBothFeedback(true);
			
		}
		
		List<ApplicantDetailsEntity> applicant = applicantLogic.getApplicantDetailsByIdPk(applicantIdPk);

		List<String> members = new ArrayList<>();

		int firstRow = 0;
		for (ApplicantDetailsEntity app : applicant) {

			if (firstRow == 0) {

				outDto.setApplicantIdPk(app.getApplicantIdPk());

				outDto.setEmail(app.getEmail());

				outDto.setAgreeFlg(app.getAgreeFlg());

				outDto.setProjectTitle(app.getProjectTitle());

				outDto.setProjectDescription(app.getProjectDescription());

				List<String[]> teams = new ArrayList<>();

				for(int i = 0; i < app.getTeams().length; i++) {
					teams.add(app.getTeams()[i].split("\\|"));
				}

				outDto.setTeams(teams);

				outDto.setProblemStatement(app.getProblemStatement());

				outDto.setTargetMarket(app.getTargetMarket());

				outDto.setSolutionDescription(app.getSolutionDescription());

				List<String[]> historicallTimelines = new ArrayList<>();

				for(int i = 0; i < app.getHistoricalTimeline().length; i++) {
					if(!app.getHistoricalTimeline()[i].equals("|")) {
						historicallTimelines.add(app.getHistoricalTimeline()[i].split("\\|"));
					}else {
						historicallTimelines.add(new String[]{"", ""});

					}
				}

				outDto.setHistoricalTimeline(historicallTimelines);

				outDto.setProductServiceOffering(Arrays.asList(app.getProductServiceOffering()));

				outDto.setPricingStrategy(Arrays.asList(app.getPricingStrategy()));

				outDto.setIntPropertyStatus(app.getIntPropertyStatus());

				outDto.setObjectives(app.getObjectives());

				outDto.setScopeProposal(app.getScopeProposal());

				outDto.setMethodology(app.getMethodology());

				outDto.setVitaeFileName(app.getVitaeFile());

				outDto.setSupportLink(app.getSupportLink());

				outDto.setGroupName(app.getGroupName());

				outDto.setGroupLeader(app.getLeaderFirstName() + ", " + app.getLeaderLastName());

				outDto.setLeaderNumber(app.getMobileNumber());

				outDto.setLeaderAddress(app.getAddress());

				outDto.setUniversity(app.getUniversity());

				outDto.setTechnologyAns(app.getTechnologyAns());

				outDto.setProductDesignAns(app.getProductDesignAns());

				outDto.setCompetitiveLandscapeAns(app.getCompetitiveLandscapeAns());

				outDto.setProductDevelopmentAns(app.getProductDevelopmentAns());

				outDto.setTeamAns(app.getTeamAns());

				outDto.setGoToMarketAns(app.getGoToMarketAns());

				outDto.setManufacturingAns(app.getManufacturingAns());

				outDto.setEligibilityAgreeFlg(app.getEligibilityAgreeFlg());

				outDto.setCommitmentOneFlg(app.getCommitmentOneFlg());

				outDto.setCommitmentTwoFlg(app.getCommitmentTwoFlg());

				outDto.setCommitmentThreeFlg(app.getCommitmentThreeFlg());

				outDto.setCommitmentFourFlg(app.getCommitmentFourFlg());
				
			}
			 
			members.add(app.getMemberLastName()  + ", " + app.getMemberFirstName());
			
			firstRow++;
		}
		
		outDto.setMembers(members);
		
		
		return outDto;
	}

	@Override
	public ApplicantInOutDto getUserReapply(ApplicantInOutDto inDto) {
		
		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		int userIdPk = 0;
		
		String email = "";
		
		if(inDto.getToken().charAt(0)=='F') {	
			UserInformationEntity eUser = userLogic.getUserByEvaluatedToken(inDto.getToken());
			
			userIdPk = eUser.getIdPk();
			
			email = eUser.getEmail();
			
		}else {
			UserInformationEntity rUser = userLogic.getUserByRejectedToken(inDto.getToken());
			
			userIdPk = rUser.getIdPk();
			
			email = rUser.getEmail();
		}
	
		outDto.setApplicantIdPk(userIdPk);
		
		outDto.setEmail(email);
		
		return outDto;
	}
	
	

}