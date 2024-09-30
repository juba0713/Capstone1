package capstone.model.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.common.constant.MessageConstant;
import capstone.model.dao.UserInformationDao;
import capstone.model.dao.entity.AdminDashboardEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserDetailsEntity;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dao.entity.UserInformationEntity;
import capstone.model.dto.AdminInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.AdminDashboardObj;
import capstone.model.object.ApplicantObj;
import capstone.model.object.ErrorObj;
import capstone.model.object.UserDetailsObj;
import capstone.model.service.AdminService;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private UserLogic userLogic;
	
	@Autowired
	private ApplicantLogic applicantLogic;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public AdminInOutDto getAdminDashboardDetails() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		AdminDashboardEntity entity = userLogic.getDetailsForAdminDashboard();
		
		AdminDashboardObj obj = new AdminDashboardObj();
		
		obj.setApplicationPassedCount(entity.getApplicationPassedCount());
		
		obj.setApplicationFailedCount(entity.getApplicationFailedCount());
		
		obj.setApplicationAcceptedCount(entity.getApplicationAcceptedCount());
		
		obj.setApplicationRejectedCount(entity.getApplicationRejectedCount());
		
		obj.setInOfficerCount(entity.getInOfficerCount());
		
		obj.setInTbiBoardCount(entity.getInTbiBoardCount());
		
		obj.setInManagerCount(entity.getInManagerCount());
		
		obj.setApplicantCount(entity.getApplicantCount());
		
		obj.setOfficerCount(entity.getOfficerCount());
		
		obj.setTbiboardCount(entity.getTbiboardCount());
		
		obj.setManagerCount(entity.getManagerCount());
		
		obj.setActivatedAccountCount(entity.getActivatedAccountCount());
		
		obj.setReapplicationRejectedCount(entity.getReapplicationRejectedCount());
		
		obj.setReapplicationFailedCount(entity.getReapplicationFailedCount());
		
		obj.setIssuedCertificateCount(entity.getIssuedCertificateCount());
		
		outDto.setAdminDashboardObj(obj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto getAllUsers() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		 List<UserDetailsEntity> users = userLogic.getAllUsers();
		
		List<UserDetailsObj> usersObj = new ArrayList<>();
		
		for(UserDetailsEntity user : users) {
			
			UserDetailsObj obj = new UserDetailsObj();
			
			obj.setId(user.getIdPk());
			
			obj.setEmail(user.getEmail());
			
			obj.setNumber(user.getMobileNumber());
			
			obj.setRole(user.getRole());
			
			
			
			obj.setCreatedDate(user.getCreatedDate());
			
			obj.setUpdatedDate(user.getUpdatedDate());
			
			obj.setDeletable(user.getDeletable());
			
			usersObj.add(obj);
			
		}
		
		outDto.setAllUsers(usersObj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto getAllApplicants() {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		List<Integer> status = List.of(0,1,2,3,4,5,6,7);
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicantByStatus(status);
		
		List<ApplicantObj> applicantsObj = new ArrayList<>();
		
		for(JoinApplicantProject app : listOfApplicant) {
	
			ApplicantObj obj = new ApplicantObj();
			
			obj.setApplicantIdPk(app.getApplicantIdPk());
			
			obj.setEmail(app.getEmail());
			
			obj.setProjectTitle(app.getProjectTitle());
					
			obj.setStatus(app.getStatus());
			
			obj.setTotalRating(app.getTotalRating());;
			
			obj.setUniversity(app.getUniversity());
			
			obj.setAcceptedBy(app.getAcceptedBy());
			
			obj.setEvaluatedBy(app.getEvaluatedBy());
			
			applicantsObj.add(obj);
			
		}
		
		outDto.setAllApplicants(applicantsObj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto validateInputs(AdminInOutDto inDto) {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		ErrorObj error = new ErrorObj();

		List<String> currentPasswordError = new ArrayList<>();

		List<String> newPasswordError = new ArrayList<>();

		List<String> confirmPasswordError = new ArrayList<>();
		
		List<String> emailError = new ArrayList<>();
		
		String roleError = CommonConstant.BLANK;
		
		String mobileNumberError = CommonConstant.BLANK;
		
		String firstNameError = CommonConstant.BLANK;
		
		String lastNameError = CommonConstant.BLANK;
		
		if (inDto.getUserIdPk() == 0 && (inDto.getEmail() == null || inDto.getEmail().isEmpty())) {
			
			emailError.add(MessageConstant.EMAIL_BLANK);
			
			outDto.setResult(CommonConstant.INVALID);
		}
		if (inDto.getUserIdPk() == 0 && !CommonConstant.EMAIL_PATTERN.matcher(inDto.getEmail()).matches()) {
			
			emailError.add(MessageConstant.EMAIL_INCORRECT_FORMAT);
			
			outDto.setResult(CommonConstant.INVALID);
		}
		
		if (inDto.getMobileNumber() == null || inDto.getMobileNumber().isEmpty()) {

			mobileNumberError = MessageConstant.MOBILE_NUMBER_BLANK;

			outDto.setResult(CommonConstant.INVALID);
		}
		
		if (inDto.getFirstName() == null || inDto.getFirstName().isEmpty()) {

			firstNameError = MessageConstant.FIRST_NAME_BLANK;

			outDto.setResult(CommonConstant.INVALID);
		}
		
		if (inDto.getLastName() == null || inDto.getLastName().isEmpty()) {

			lastNameError = MessageConstant.LAST_NAME_BLANK;

			outDto.setResult(CommonConstant.INVALID);
		}
		
		if(inDto.getRole() == null || inDto.getRole().equals("none")){
			
			roleError = MessageConstant.ROLE_BLANK;
			
			outDto.setResult(CommonConstant.INVALID);
		}
				
		if (inDto.getUserIdPk() == 0 &&  (inDto.getPassword() == null || inDto.getPassword().isEmpty())) {

			newPasswordError.add(MessageConstant.PASSWORD_BLANK);

			outDto.setResult(CommonConstant.INVALID);
		}

		if (!inDto.getPassword().equals(inDto.getConfirmPassword())) {

			newPasswordError.add(MessageConstant.NEW_CONFIRM_EQUAL_ERROR);

			outDto.setResult(CommonConstant.INVALID);

		}

		if (inDto.getUserIdPk() == 0 && ( inDto.getConfirmPassword() == null || inDto.getConfirmPassword().isEmpty())) {

			confirmPasswordError.add(MessageConstant.CONFIRM_PASSWORD_BLANK);

			outDto.setResult(CommonConstant.INVALID);
		}

		if (CommonConstant.INVALID.equals(outDto.getResult())) {

			error.setNewPasswordError(newPasswordError);

			error.setConfirmPasswordError(confirmPasswordError);

			error.setCurrentPasswordError(currentPasswordError);
			
			error.setEmailError(emailError);
			
			error.setRoleError(roleError);
			
			error.setMobileNumberError(mobileNumberError);
			
			error.setFirstNameError(firstNameError);
			
			error.setLastNameError(lastNameError);
			
			outDto.setErrors(error);
		} else {
			outDto.setResult(CommonConstant.VALID);
		}

		return outDto;
	}

	@Override
	public void saveUser(AdminInOutDto inDto) {
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
	
		UserInformationEntity newUser = new UserInformationEntity();
		
		newUser.setEmail(inDto.getEmail());
		
		newUser.setMobileNumber(inDto.getMobileNumber());
		
		newUser.setFirstName(inDto.getFirstName());
		
		newUser.setLastName(inDto.getLastName());
		
		newUser.setRole(inDto.getRole());
		
		newUser.setInitialChangePass(false);
		
		newUser.setCreatedDate(timeNow);
		
		newUser.setUpdatedDate(timeNow);
		
		newUser.setDeleteFlg(false);
		
		int idPk = userLogic.saveUser(newUser);
		
		UserInfoAccountEntity newUserAccount = new UserInfoAccountEntity();
		
		newUserAccount.setUserIdPk(idPk);
		
		newUserAccount.setPassword(encoder.encode(inDto.getPassword()));
		
		newUserAccount.setCreatedDate(timeNow);
		
		newUserAccount.setDeleteFlg(false);
		
		userLogic.saveUserAccount(newUserAccount);
	}

	@Override
	public void deleteUser(AdminInOutDto inDto) {
		
		userLogic.deleteUser(inDto.getUserIdPk());

	}

	@Override
	public AdminInOutDto getUserDetails(AdminInOutDto inDto) {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		UserInformationEntity entity = userLogic.getUserByIdPk(inDto.getUserIdPk());
		
		UserDetailsObj obj = new UserDetailsObj();
		
		obj.setId(entity.getIdPk());
		
		obj.setEmail(entity.getEmail());
		
		obj.setFirstName(entity.getFirstName());
		
		obj.setLastName(entity.getLastName());
		
		obj.setNumber(entity.getMobileNumber());
		
		obj.setRole(entity.getRole());
				
		if(entity.getRole().equals("APPLICANT")) {
			obj.setIsApplicant(true);
		}else {
			obj.setIsApplicant(false);
		}
		
		outDto.setUser(obj);
		
		return outDto;
	}

	@Override
	public AdminInOutDto updateUser(AdminInOutDto inDto) {
		
		AdminInOutDto outDto = new AdminInOutDto();
		
		outDto.setResult(CommonConstant.VALID);
		
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
		
		int result = userLogic.updateuser(inDto.getFirstName(), inDto.getLastName(), inDto.getMobileNumber(), inDto.getRole(), timeNow, inDto.getUserIdPk());
	
		if(result == 0) {
			outDto.setResult(CommonConstant.INVALID);
		}
		
		return outDto;
	}

}
