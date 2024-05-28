package capstone.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import capstone.model.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public List<String> convertToList(String[] val) {
		
	List<String> newList = new ArrayList<>();
		
		for(String v : val) {
			String[] splitV = v.split("|");
			
			newList.add(splitV[0]+"("+splitV[1]+")");
			
		}
		
		return newList;
	}

	@Override
	public List<String> convertToArray(List<String[]> var) {
		
		List<String> convertedValue = new ArrayList<>();
		
		if(var != null && !var.isEmpty()) {
			
			for(String[] value : var) {
				
				String stringValue = String.join("|", value);
				
				convertedValue.add(stringValue);
			}	
		}
			
		return convertedValue;
	}

	@Override
	public String[] splitArray(String var) {

		return var.split(",");
	}

	@Override
	public String generateRandomPass() {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }

        return result.toString();
	}

}
