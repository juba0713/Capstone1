package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.TbiBoardInOutDto;
import jakarta.mail.MessagingException;

@Service
public interface TbiBoardService {
	
	/**
	 * To get analytics details for Tbi Board Dashboard
	 * @return
	 */
	public TbiBoardInOutDto getDetailsForTbiBoardDashboard();

	/**
	 * TO get all applicants for TbiBOard
	 * @return ManagerInOutDto
	 */
	public TbiBoardInOutDto getAllApplicants() throws Exception ;
	
	/**
	 * To evaluate applicant
	 * @return
	 */
	public void evaluateApplicant(TbiBoardInOutDto inDto) throws MessagingException;
	
	/**
	 * To get the details of the application by id pk
	 * @param inDto
	 * @return ApplicantInOutDto
	 */
	public TbiBoardInOutDto getApplicantDetails(TbiBoardInOutDto inDto) throws Exception ; 
}
