package capstone.model.service.impl;

import org.springframework.stereotype.Service;

import capstone.common.CommonConstant;
import capstone.model.dto.ApplicantInOutDto;
import capstone.model.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	@Override
	public ApplicantInOutDto saveApplicant(ApplicantInOutDto inDto) {
		
		ApplicantInOutDto outDto = new ApplicantInOutDto();
		
		
		outDto.setResult(CommonConstant.VALID);
		
		return outDto;
	}

}