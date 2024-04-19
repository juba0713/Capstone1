package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.ApplicantInOutDto;

@Service
public interface ApplicantService {
	
	/**
	 * To valdiate applicant's input
	 * @param inDto
	 * @return ApplicantInOutDto
	 */
	public ApplicantInOutDto validateApplicant(ApplicantInOutDto inDto);

	/**
	 * To save applicant
	 * @param inDto
	 * @return ApplicantInOutDto
	 */
	public ApplicantInOutDto saveApplicant(ApplicantInOutDto inDto);
}