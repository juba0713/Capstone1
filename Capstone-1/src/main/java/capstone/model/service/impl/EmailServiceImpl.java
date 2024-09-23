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
	public void sendRejectionMail(String feedback, boolean resubmitFlg, String email, String token)
			throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Denied");

		String htmlText = "<h1>Feedback:</h1>" +
				"<p>" + feedback + "</p>" +
				"<p>" + "Reapplication: " + (resubmitFlg ? "Yes" : "No") + "</p>";

		if (resubmitFlg) {
			htmlText += "<div>Please review the feedback and consider resubmitting a revised application.</div>"
					+ "<a href='http://localhost:8080/applicant/form/resubmit?token=" + token + "'>"
					+ "resubmit</a>";
		} else {
			htmlText += "<div>You are not qualified to resubmit this application. Please consider submitting a new application if you wish to reapply.</div>"
					+ "<a href='http://localhost:8080/applicant/form?token=" + token + "'>"
					+ "reapply</a>";
		}

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendActivationMail(String password, String email) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Account Activated");

		String htmlText = "<h1>Welcome!</h1>" +
				"<p>Your account has been activated.</p>" +
				"<p>Your email: </p>" + email + "</p>" +
				"<p>Your password: " + password + "</p>" +
				"<div><a href='http://localhost:8080/login'>Login Now</a></div>";

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendFailedMail(boolean resubmitFlg, String email, String token)
			throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Failed");

		String htmlText = "<div>Your application did not pass the evaluation, as it did not reach the passing score of 6 out of 10. </div> ";

		if (resubmitFlg) {
			htmlText += "<div>Please review the feedback and consider resubmitting a revised application.</div>"
					+ "<a href='http://localhost:8080/applicant/form/resubmit?token=" + token + "'>"
					+ "resubmit</a>";
		} else {
			htmlText += "<div>You are not qualified to resubmit this application. Please consider submitting a new application if you wish to reapply.</div>"
					+ "<a href='http://localhost:8080/applicant/form?token=" + token + "'>"
					+ "reapply</a>";
		}

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendAcceptedMail(String email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Accepted");

		String htmlText = "<div>Your application has been accepted!. Please wait for more further instructions.</div> ";

		helper.setText(htmlText, true);

		emailSender.send(message);
	}

	@Override
	public void sendTBITransferedMail(String email) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Transferred");

		String htmlText = "<div>Your application has been transferred to the board for evaluation!. Please wait for the result..</div> ";

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendEvaluatedMail(String email) throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Evaluated");

		String htmlText = "<div>Your application has been evaluated!. Please <a href='http://localhost:8080/login'>login</a> to see the result.</div> ";

		helper.setText(htmlText, true);

		emailSender.send(message);
	}

	@Override
	public void sendIssuedCertificate(String email) throws MessagingException {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    helper.setFrom(CommonConstant.EMAIL);
	    helper.setTo(email);
	    helper.setSubject("Certificate Issued");

	    String htmlText = "<div>"
	            + "<h1>Congratulations!</h1>"
	            + "<p>We are pleased to inform you that your certificate has been successfully issued.</p>"
	            + "<p>You can now download or access your certificate by logging into your account.</p>"
	            + "<br>"
	            + "<p>Thank you for your efforts and dedication.</p>"
	            + "<p>Best regards,<br>The Certification Team</p>"
	            + "</div>";

	    helper.setText(htmlText, true);

	    emailSender.send(message);
	}

	@Override
	public void sendEvaluatedMailManager(String email) throws MessagingException {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    helper.setFrom(CommonConstant.EMAIL);
	    helper.setTo(email);
	    helper.setSubject("Your Application Has Been Evaluated");

	    String htmlText = "<div>"
	            + "<h1>Your Application Has Been Evaluated!</h1>"
	            + "<p>We are pleased to inform you that your application has been evaluated successfully.</p>"
	            + "<p>You can now log in to your account to check your ranking and see where you stand in the evaluation process.</p>"
	            + "<br>"
	            + "<p>Thank you for your participation and effort.</p>"
	            + "<p>Best regards,<br>The Evaluation Team</p>"
	            + "</div>";

	    helper.setText(htmlText, true);

	    emailSender.send(message);
	}



}
