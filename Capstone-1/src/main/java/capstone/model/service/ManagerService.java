package capstone.model.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import capstone.model.dto.ManagerInOutDto;
import jakarta.mail.MessagingException;

@Service
public interface ManagerService {
	
	/**
	 * TO get all applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllApplicants() throws Exception;
	
	/**
	 * TO create an account for applicant
	 * @params inDto
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto activateApplicantAccount(ManagerInOutDto inDto) throws MessagingException;
	
	/**
	 * To update applicant status
	 * @param inDto
	 * @return ManagerInOutDto
	 */
	public void updateApplicantStatus(ManagerInOutDto inDto) throws MessagingException ;
	
	/**
	 * TO get all evaluated applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllEvaluatedApplicants();
	
	/**
	 * TO get all accepted applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllAcceptedApplicants();
	
	/**
	 * To get the details of the application by id pk
	 * @param inDto
	 * @return ApplicantInOutDto
	 */
	public ManagerInOutDto getApplicantDetails(ManagerInOutDto inDto); 
	
	/**
	 * To esnd a resubmission mail
	 * @param inDto
	 * @return
	 */
	public ManagerInOutDto sendResubmissionMail(ManagerInOutDto inDto)  throws MessagingException;
	
	public ManagerInOutDto getAppllicantOnTodayMonth() throws Exception;
	
	public ManagerInOutDto getApplicantDetailsWithFeedback(ManagerInOutDto inDto);
	
}
