package capstone.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {

	public List<String> convertToList(String[] val);
	
	public List<String> convertToArray(List<String[]> var);
	
	public String[] listToArray(List<String> var);
	
	public String[] splitArray(String var);
	
	public String generateRandomPass();
}
