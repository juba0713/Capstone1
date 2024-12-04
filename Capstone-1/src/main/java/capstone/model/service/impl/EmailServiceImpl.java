package capstone.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import capstone.common.constant.CommonConstant;
import capstone.model.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendRejectionMail(String feedback, boolean resubmitFlg, String email, String token)
			throws MessagingException {

		String siteURL = getSiteUrl();

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Denied");

		String htmlText = "<h1>Feedback:</h1>";
		// +
		// "<p>" + "Reapplication: " + (resubmitFlg ? "Yes" : "No") + "</p>";

		if (resubmitFlg) {
			htmlText += "<div>Please review the feedback and consider resubmitting a revised application.</div>"
					+ "<a href='" + siteURL + "/applicant/form/resubmit?token=" + token + "'>"
					+ "resubmit</a>";
		} else {
			htmlText += "<div>You are not qualified to resubmit this application. Please consider submitting a new application if you wish to reapply.</div>"
					+ "<a href='" + siteURL + "/applicant/form?token=" + token + "'>"
					+ "reapply</a>";
		}

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendActivationMail(String password, String email) throws MessagingException {

		String siteURL = getSiteUrl();

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Account Activated");

		String htmlText = "<h1>Welcome!</h1>" +
				"<p>Your account has been activated.</p>" +
				"<p>Your email:  " + email + "</p>" +
				"<p>Your password: " + password + "</p>" +
				"<div><a href='" + siteURL + "/login'>Login Now</a></div>";

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendFailedMail(boolean resubmitFlg, String email, String token)
			throws MessagingException {

		String siteURL = getSiteUrl();

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Failed");

		String htmlText = "<div>We appreciate your effort in applying, but your application didn’t pass the evaluation this time.</div> ";

		if (resubmitFlg) {
			htmlText += "<div>Please review the feedback provided and consider submitting an updated version for re-evaluation.</div>"
					+ "<a href='" + siteURL + "/applicant/form/resubmit?token=" + token + "'>"
					+ "resubmit</a>";
		} else {
			htmlText += "<div>If you would like to reapply, please submit a new application.</div>"
					+ "<a href='" + siteURL + "/applicant/form?token=" + token + "'>"
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

		String htmlText = "<div>Your application is currently in the Initial Review stage. We will keep you informed about any updates as we move forward in the process.</div>";

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

		String htmlText = "<div>Your application has moved to the evaluation stage with the board. We will notify you with the outcome once the review is complete.</div>";

		helper.setText(htmlText, true);

		emailSender.send(message);

	}

	@Override
	public void sendEvaluatedMail(String email) throws MessagingException {
		String siteURL = getSiteUrl();

		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(CommonConstant.EMAIL);
		helper.setTo(email);
		helper.setSubject("Application Evaluated");

		String htmlText = "<div>Your application has been evaluated! Please <a href='" + siteURL
				+ "/login'>login</a> to see the result. We will keep you posted on the next steps. Thank you for your patience!</div>";
		helper.setText(htmlText, true);

		emailSender.send(message);
	}

	@Override
	public void sendIssuedCertificate(String email, String fileName) throws MessagingException {

		String siteURL = getSiteUrl();

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
				+ "</div>"
				+ ""
				+ "<a href='" + siteURL + "/download/certificate/" + fileName
				+ ".png' >Click here to download the certificate</a>";

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

	public String getSiteUrl() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attrs.getRequest();

		// Generate site URL dynamically using the request object
		String siteURL = request.getRequestURL().toString();
		String sitePATH = request.getServletPath();
		siteURL = siteURL.replace(sitePATH, "");
		return siteURL;
	}

}
