package capstone.model.service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public interface EmailService {
	
	public void sendRejectionMail(String feedback, boolean resubmitFlg, String email) throws MessagingException;

	public void sendActivationMail(String password, String email) throws MessagingException;
	
	
}
