package com.iconnect.profiling.mail;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.iconnect.profiling.domain.ActivationDomain;

/**
 * @author NaveenKumar
 * 
 */
public class Mail {

	private Logger logger = LoggerFactory.getLogger(Mail.class);
	private ActivationDomain activationModel;
	private JavaMailSenderImpl impl;
	
	// initialize the variables using constructor
	public Mail(ActivationDomain activationModel, JavaMailSenderImpl impl) {
		this.activationModel = activationModel;
		this.impl = impl;
	}

	// Below method allows us to send a mail
	public void sendMail() {
		MimeMessage mimeMessage = impl.createMimeMessage();
		try {
			System.out.println("************* Entered mail thread"
					+ activationModel.getEmailId());
			synchronized (this) {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
						true);
				/*
				 * String htmlMsg =
				 * "<html></body><font color='green'>Hi this dummy activation code</font>"
				 * + "<a href='http://localhost:1000/IConnect/registration/'" +
				 * activationHelper
				 * .getActivationCode()+"'/'/></a></body></html>";
				 * mimeMessage.setContent(htmlMsg, "text/html");
				 */

				helper.setFrom("nvkumar55@gmail.com");
				helper.setTo(activationModel.getEmailId());
				helper.setSubject("ActivationMail");
				helper.setText("please click on the link to activate account",
						"http://localhost:1001/IConnect/registration/activate?activationCode="
								+ activationModel.getActivationCode());
				helper.setFrom("Naveen", "Please click on link to activate");
				/*
				 * uncomment the following lines for attachment
				 * FileSystemResource file = new
				 * FileSystemResource("attachment.jpg");
				 * helper.addAttachment(file.getFilename(), file);
				 */

				impl.send(mimeMessage);
				logger.debug("Mail Sent SuccessFully");

			}
		} catch (MessagingException | UnsupportedEncodingException e) {
			logger.debug("Mail Sending Failed");
		}

	}
}
