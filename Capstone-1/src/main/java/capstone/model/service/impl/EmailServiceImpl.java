package capstone.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import capstone.common.constant.CommonConstant;
import capstone.model.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender emailSender;

	@Override
	public void sendRejectionMail(String feedback, boolean resubmitFlg, String email) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(CommonConstant.EMAIL);
	    helper.setTo(email);
	    helper.setSubject("Application Denied");
	    
	    String htmlText = "<h1>Feedback:</h1>" +
                "<p>" + feedback + "</p>" +
                "<p>" + "Reapplication: " + (resubmitFlg ? "Yes" : "No") + "</p>"; 

	    helper.setText(htmlText, true); 
		
	    emailSender.send(message);
		
	}

	@Override
	public void sendActivationMail(String password, String email) throws MessagingException{
		
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom(CommonConstant.EMAIL);
	    helper.setTo(email);
	    helper.setSubject("Account Activated");
	    
	    String htmlText = "<h1>Welcome!</h1>" +
                "<p>Your account has been activated.</p>" +
                "<p>Your email: </p>" + email + "</p>" +
                "<p>Your password: " + password + "</p>" +
                "<div><a href='http://your-website.com/login'>Login Now</a></div>"; 

	    helper.setText(htmlText, true); 
		
	    emailSender.send(message);
	    		
	}

}
