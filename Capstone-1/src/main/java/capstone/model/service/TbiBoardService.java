package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.TbiBoardInOutDto;

@Service
public interface TbiBoardService {

	/**
	 * TO get all applicants for TbiBOard
	 * @return ManagerInOutDto
	 */
	public TbiBoardInOutDto getAllApplicants();
	
	/**
	 * To evaluate applicant
	 * @return
	 */
	public void evaluateApplicant(TbiBoardInOutDto inDto);
}
