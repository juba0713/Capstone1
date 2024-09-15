package capstone.model.service;

import java.security.InvalidKeyException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CommonService {

	public List<String> convertToList(String[] val);
	
	public List<String> convertToArray(List<String[]> var);
	
	public String[] listToArray(List<String> var);
	
	public String[] splitArray(String var);
	
	public String generateRandomPass();
	
	public String encrypt(String value) throws Exception;
	
	public String decrypt(String encryptedValue) throws Exception;
}
