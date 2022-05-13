package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import project.dao.AccountDao;
import project.model.AccountModel;

@Service
public class EmailService {

	AccountDao dao;
	RandomGeneration random;
	Pbkdf2PasswordEncoder encoder;
	private JavaMailSender emailSender;
	
	@Autowired
	public EmailService(AccountDao dao, RandomGeneration random, Pbkdf2PasswordEncoder encoder,
			JavaMailSender emailSender) {
		super();
		this.dao = dao;
		this.random = random;
		this.encoder = encoder;
		this.emailSender = emailSender;
	}
	
	/**
	 * Generates and sends a predetermined password reset email to the inputted "to" value
	 * 
	 * @param to
	 */
	
	public void sendMessage(String to) {
		System.out.println(to);
		String randomPassword = random.generateRandomPassword();
		System.out.println(randomPassword);
		String encodedRandomPassword = encoder.encode(randomPassword);
		System.out.println(encodedRandomPassword);
		AccountModel passwordResetUser = dao.findByEmail(to);
		passwordResetUser.setPassword(encodedRandomPassword);
		
		dao.save(passwordResetUser);
		
		String name = passwordResetUser.getFirstName();
		
		String subject = "Password Reset";
		

		String text = "Hi " + name +","
						+ "\n\nForgot your password? "
						+ "\n\nWe recieved a request to reset the password on your account."
						+ "\n\nIf you wish to reset your password, please use the following code as your temporary password.\n\n"
						+ randomPassword; // we can change this to be whatever, even generating it somehow if we want to figure that out.

		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("noreply@ourpage.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}
	
}
