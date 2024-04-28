package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.ManagerInOutDto;
import jakarta.mail.MessagingException;

@Service
public interface ManagerService {
	
	/**
	 * TO get all applicants
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto getAllApplicants();
	
	/**
	 * TO create an account for applicant
	 * @params inDto
	 * @return ManagerInOutDto
	 */
	public ManagerInOutDto activateApplicantAccount(ManagerInOutDto inDto) throws MessagingException;

}
