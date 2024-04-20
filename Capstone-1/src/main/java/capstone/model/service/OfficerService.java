package capstone.model.service;

import org.springframework.stereotype.Service;

import capstone.model.dto.OfficerInOutDto;

@Service
public interface OfficerService {
	
	public OfficerInOutDto getAllApplicants();
}
