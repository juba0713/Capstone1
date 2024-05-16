package capstone.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.model.dao.entity.ApplicantEntity;
import capstone.model.dao.entity.JoinApplicantProject;
import capstone.model.dao.entity.UserInfoAccountEntity;
import capstone.model.dto.ManagerInOutDto;
import capstone.model.logic.ApplicantLogic;
import capstone.model.logic.UserLogic;
import capstone.model.object.ApplicantObj;
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

	@Override
	public ManagerInOutDto getAllApplicants() {
		
		ManagerInOutDto outDto = new ManagerInOutDto();
		
		List<JoinApplicantProject> listOfApplicant = applicantLogic.getAllApplicant13();
		
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
		
		applicantEntity.setStatus(3);
		
		applicantLogic.saveApplicantEntity(applicantEntity);
		
		emailService.sendActivationMail(randomPassword, userLogic.getUserByIdPk(applicantEntity.getCreatedBy()).getEmail());
		
		UserInfoAccountEntity userAccount = userLogic.getUserAccountByUserIdPk(applicantEntity.getCreatedBy());
		
		userAccount.setPassword(encoder.encode(randomPassword));
		
		userLogic.saveUserAccount(userAccount);
	       
		return outDto;
	}
	
}
