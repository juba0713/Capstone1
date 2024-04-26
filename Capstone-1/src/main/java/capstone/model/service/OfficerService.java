package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.OfficerInOutDto;

@Service
public interface OfficerService {
	
	/**
	 * To get all applicants
	 * @return OfficerInOutDto
	 */
	public OfficerInOutDto getAllApplicants();
	
	/**
	 * To reject an applicants
	 * @param inDto
	 * @return OfficerInOutDto
	 */
	public OfficerInOutDto rejectApplicant(OfficerInOutDto inDto);
}
