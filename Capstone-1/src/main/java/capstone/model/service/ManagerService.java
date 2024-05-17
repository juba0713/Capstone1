package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.ManagerInOutDto;
import jakarta.mail.MessagingException;

@Service
public interface ManagerService {
	
	/**
	 * TO get all applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllApplicants();
	
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
	public void updateApplicantStatus(ManagerInOutDto inDto);
	
	/**
	 * TO get all evaluated applicants for manager
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllEvaluatedApplicants();
	

}
