package capstone.model.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import capstone.model.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private Environment env;

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

	@Override
	public String[] listToArray(List<String> var) {
		String[] converteValue = new String[var.size()];
		
		if(var != null && !var.isEmpty()) {
			
			int i = 0;
			for(String value : var) {
								
				converteValue[i] = value;
				i++;
			}	
		}
			
		return converteValue;
	}

	@Override
	public String encrypt(String value) throws Exception {
		String algorithm = env.getProperty("cipher.algorithm");
		byte[] secretKey = env.getProperty("cipher.secret_key").getBytes();
		SecretKey key = new SecretKeySpec(secretKey, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
	}

	@Override
	public String decrypt(String encryptedValue) throws Exception {
		String algorithm = env.getProperty("cipher.algorithm");
		byte[] secretKey = env.getProperty("cipher.secret_key").getBytes();
		SecretKey key = new SecretKeySpec(secretKey, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
	}

	@Override
	public int calculateTotalRatings(int rateOne, int rateTwo, int rateThree, int rateFour, int rateFive, int rateSix,
			int rateSeven, int rateEight) {
		
		//Multiplier
		double mOne = 1.0;
		double mTwo = 2.0;
		double mThree= 3.0;
		double mFour = 1.5;
		double divider = 1.35;
		
		double resOne = rateOne * mOne;
		double resTwo = rateTwo * mOne;
		double resThree = rateThree * mTwo;
		double resFour = rateFour * mTwo;
		double resFive = rateFive * mThree;
		double resSix = rateSix * mFour;
		double resSeven = rateSeven * mFour;
		double resEight = rateEight * mFour;
		
		double total = resOne + resTwo + resThree + resFour + resFive + resSix + resSeven + resEight;
		
		int result = (int) Math.ceil(total / divider);
		
		return result;
	}

}
