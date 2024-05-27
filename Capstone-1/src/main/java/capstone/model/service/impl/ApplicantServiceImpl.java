package capstone.model.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import capstone.model.dao.entity.ApplicantDetailsEntity;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.GroupEntity;
import capstone.model.dao.entity.GroupMemberEntity;
import capstone.model.dao.entity.ProjectEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.dto.OfficerInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantDetailsObj;
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

		Pattern EMAIL_PATTERN = Pattern
				.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

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
		String agreeFlgError = "";
		String technologyAnsError = "";
		String productDevelopmentAnsError = "";
		String CompetitiveLandscapeAnsError = "";
		String productDesignAnsError = "";
		String teamAnsError = "";
		String goToMarketAnsError = "";
		String manufacturingAnsError = "";
		String eligibilityAgreeFlgError = "";
		String commitmentOneFlgError = "";
		String commitmentTwoFlgError = "";
		String commitmentThreeFlgError = "";
		String commitmentFourFlgError = "";

		boolean hasError = false;

		if (CommonConstant.BLANK.equals(inDto.getEmail())) {
			emailError.add("Email is required!");
			hasError = true;
		}
		if (!EMAIL_PATTERN.matcher(inDto.getEmail()).matches()) {
			emailError.add("Email is incorrect format!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getProjectTitle())) {
			projectTitleError.add("Project Title is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getProjectDescription())) {
			projectDescriptionError.add("Project Description is required!");
			hasError = true;
		}

		for (String[] team : inDto.getTeams()) {
			if (team[0].length() == 0 || team[1].length() == 0) {
				teamsError.add("Member name and Input role cannot be empty!");
				hasError = true;
				break;
			}
		}

		if (CommonConstant.BLANK.equals(inDto.getProblemStatement())) {
			problemStatementError.add("Problem Statement is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getTargetMarket())) {
			targetMarketError.add("Problem Statement is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getSolutionDescription())) {
			solutionDecriptionError.add("Problem Statement is required!");
			hasError = true;
		}

		for (String[] time : inDto.getHistoricalTimeline()) {
			if (!time[0].isEmpty() && time[1].isEmpty() || time[0].isEmpty() && !time[1].isEmpty()) {
				historicalTimelineError.add("Please ensure month and year and the key activities are filled in!");
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
			productServiceOfferingError.add(
					"Please describe the key activities or milestones for your product/service offering and pricing strategy for at least one competitor or alternative!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getObjectives())) {
			objectivesError.add("Objectives is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getScopeProposal())) {
			scopeProposalError.add("Scope of the Proposal is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getMethodology())) {
			methodologyError.add("Methodology and Expected Outputs is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getGroupName())) {
			groupNameError.add("Group Name is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getGroupLeader())) {
			groupLeaderError.add("Group Leader is required!");
			hasError = true;
		}

		if (inDto.getGroupLeader().split(",").length != 2) {
			groupLeaderError.add("Incorrect Format! (Lastname, Firstname)");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getLeaderNumber())) {
			leaderNumberError.add("Mobile Number is required!");
			hasError = true;
		}

		if (CommonConstant.BLANK.equals(inDto.getLeaderAddress())) {
			leaderAddressError.add("Address is required!");
			hasError = true;
		}

		for (String member : inDto.getMembers()) {
			if (member.split(",").length != 2 && !CommonConstant.BLANK.equals(member)) {
				membersError.add("Incorrect Format! (Lastname, Firstname)");
				hasError = true;
				break;
			}
		}

		if (inDto.getAgreeFlg() == null) {
			agreeFlgError = "Please select whether you agree or disagree to the terms!";
			hasError = true;
		}

		if (inDto.getTechnologyAns() == 0) {
			technologyAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getProductDevelopmentAns() == 0) {
			productDevelopmentAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getCompetitiveLandscapeAns() == 0) {
			CompetitiveLandscapeAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getProductDesignAns() == 0) {
			productDesignAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getTeamAns() == 0) {
			teamAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getGoToMarketAns() == 0) {
			goToMarketAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getManufacturingAns() == 0) {
			manufacturingAnsError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getEligibilityAgreeFlg() == null) {
			eligibilityAgreeFlgError = "Please select whether you agree or disagree to the Egilibility Agreement!";
			hasError = true;
		}

		if (inDto.getCommitmentOneFlg() == null) {
			commitmentOneFlgError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getCommitmentTwoFlg() == null) {
			commitmentTwoFlgError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getCommitmentThreeFlg() == null) {
			commitmentThreeFlgError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getCommitmentFourFlg() == null) {
			commitmentFourFlgError = "Please select an option!";
			hasError = true;
		}

		if (inDto.getVitaeFile().isEmpty()) {
			vitaeFileError.add("Please upload a Curriculum Vitae");
			hasError = true;
		}

		String extension = FilenameUtils.getExtension(inDto.getVitaeFile().getOriginalFilename()).toLowerCase();
		if (!extension.equals("pdf")) {
			vitaeFileError.add("Please upload a PDF File");
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

		Path uploadPath = Paths.get(env.getProperty("file.path"));

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

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

		groupEntity.setFirstName(leaderNames[0]);

		groupEntity.setLastName(leaderNames[1]);

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
				;
			}

			members[firstRow] = app.getMemberLastName() + ", " + app.getMemberFirstName();

			firstRow++;
		}

		applicantDetailsObj.setMembers(members);

		outDto.setApplicantDetailsObj(applicantDetailsObj);

		return outDto;
	}

}