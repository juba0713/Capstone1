package capstone.model.service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;

@Service
public interface EmailService {
	
	public void sendRejectionMail(String feedback, boolean resubmitFlg, String email, String token) throws MessagingException;

	public void sendActivationMail(String password, String email) throws MessagingException;
	
	public void sendFailedMail(boolean resubmitFlg, String email, String token) throws MessagingException;
	
	public void sendAcceptedMail(String email) throws MessagingException;
	
	public void sendTBITransferedMail(String email) throws MessagingException;
	
	public void sendEvaluatedMail(String email) throws MessagingException;
	
	public void sendIssuedCertificate(String email) throws MessagingException;
	
	public void sendEvaluatedMailManager(String email) throws MessagingException;
}
