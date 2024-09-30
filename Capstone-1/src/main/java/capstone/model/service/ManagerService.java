package capstone.model.service;


import org.springframework.stereotype.Service;

import capstone.model.dto.ManagerInOutDto;
import jakarta.mail.MessagingException;

@Service
public interface ManagerService {
	
	/**
	 * Get analytics details for manager dashboard
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getDetailsForManagerDashboard();
	
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
	public ManagerInOutDto getAllEvaluatedApplicants() throws Exception;
	
	/**
	 * TO get all accepted applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllAcceptedApplicants() throws Exception;
	
	/**
	 * To get the details of the application by id pk
	 * @param inDto
	 * @return ApplicantInOutDto
	 */
	public ManagerInOutDto getApplicantDetails(ManagerInOutDto inDto); 
	
	/**
	 * To esnd a resubmission mail
	 * @param inDto
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto sendResubmissionMail(ManagerInOutDto inDto)  throws MessagingException;
	
	/**
	 *  To get Applicant By Monthly
	 * @return ManagerInOutDto
	 * @throws Exception
	 */
	public ManagerInOutDto getAppllicantOnTodayMonth() throws Exception;
	
	/**
	 * To get applicant details with feedbacks
	 * @param inDto
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getApplicantDetailsWithFeedback(ManagerInOutDto inDto) throws Exception;
	
	/**
	 * Issue a certificate to the selected user
	 * @param inDto
	 * @return ManagerInOutDto
	 * @throws MessagingException
	 */
	public ManagerInOutDto issuedCertificate(ManagerInOutDto inDto) throws MessagingException;
	
	/**
	 * Evaluate the user by manager
	 * @param inDto
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto evaluateApplicant(ManagerInOutDto inDto) throws MessagingException;
	
	/**
	 *  To get Applicant Ranking By Monthly
	 * @return ManagerInOutDto
	 * @throws Exception
	 */
	public ManagerInOutDto getAppllicantRankingOnTodayMonth() throws Exception;
	
	/**
	 *  To get Applicant Ranking By Monthly
	 * @return ManagerInOutDto
	 * @throws Exception
	 */
	public ManagerInOutDto getAppllicantRankingByYearMonth(ManagerInOutDto inDto) throws Exception;
	
}
