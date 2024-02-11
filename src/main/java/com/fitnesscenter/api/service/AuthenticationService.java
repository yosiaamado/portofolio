package com.fitnesscenter.api.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitnesscenter.api.IService.IAuthenticationService;
import com.fitnesscenter.api.IService.IUserService;
import com.fitnesscenter.api.model.Authentication;
import com.fitnesscenter.api.model.ForgetPassword;
import com.fitnesscenter.api.model.User;
import com.fitnesscenter.api.repository.AuthenticationRepository;
import com.fitnesscenter.api.repository.ForgetPasswordRepository;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	AuthenticationRepository authRepos;
	IUserService userService;
	ForgetPasswordRepository fpRepos;

	@Override
	public boolean getToken(String token) {
		Authentication auth = authRepos.findByToken(token);

		if (auth == null)
			return false;

		LocalDateTime expirationDate = auth.getExpiredDate().plusHours(6);

		if (expirationDate.isBefore(LocalDateTime.now()))
			return false;
		else {
			updateExpiredDate(auth);
			return true;
		}
	}

	@Override
	public Authentication updateExpiredDate(Authentication auth) {
		auth.setExpiredDate(LocalDateTime.now());
		return authRepos.save(auth);
	}
	
	@Override
	public ResponseEntity<User> getLogin(User user) {
		User usr = userService.getByEmail(user.getEmail());
		boolean isValid = verifyPassword(user.getPassword(), usr.getPassword());
		if (usr != null && isValid) {
			Optional<Authentication> authExist = authRepos.findById(usr.getId());
			if (authExist == null) {
				String token = generateToken(16);
				Authentication auth = new Authentication();
				auth.setId(usr.getId());
				auth.setToken(token);
				auth.setExpiredDate(LocalDateTime.now().plusHours(6));
				authRepos.save(auth);

				usr.setIsLogged(1);
				userService.saveUser(usr);
				return ResponseEntity.ok(usr);
			} else
				return ResponseEntity.ok(usr);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@Override
	public ResponseEntity<User> postRegister(User user){
		User usr = userService.getByEmail(user.getEmail());
		if(usr == null) {
			user.setPassword(hashPassword(user.getPassword()));
			userService.saveUser(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usr);
		}
	}

	@Override
	public void forgetPassword(String email) {
		User usr = userService.getByEmail(email);

		Integer otp = generateOTP();
		
		ForgetPassword fp = new ForgetPassword();
		fp.setUserId(usr.getId());
		fp.setOtp(otp);
		fp.setExpiredDate(LocalDateTime.now().plusMinutes(10));
		
		if (sendOTP(email, otp)) {
			fpRepos.save(fp);
		}
	}

	@Override 
	public String forgetPasswordOTP(Long userid, Integer otp) {
		ForgetPassword fp = fpRepos.getByUserId(userid);

		if (fp.getOtp() == otp && fp.getExpiredDate().isBefore(LocalDateTime.now())) {
			return "OTP Correct";
		}
		return "Invalid OTP";
	}

	@Override
	public void Logout(Long userid) {
		User usr = userService.getById(userid);
		if (usr == null)
			return;
		else {
			usr.setLastlogin(LocalDateTime.now());
			usr.setIsLogged(0);
			userService.updateUser(usr);
		}
	}

	public static String generateToken(int length) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] token = new byte[length];
		secureRandom.nextBytes(token);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
	}

	public static String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public static boolean verifyPassword(String password, String hashedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, hashedPassword);
	}

	public static Integer generateOTP() {
		Random random = new Random();
		int otp = 0;
		for (int i = 0; i < 6; i++) {
			otp = otp * 10 + random.nextInt(10);
		}
		return otp;
	}

	public static boolean sendOTP(String recipientEmail, Integer otp) {
		// mohon maaf tidak saya isi karena sifatnya private
		String senderEmail = "senderEmail@gmail.com"; // Isi email sender
		String senderPassword = "senderPassword"; // Isi Password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Your OTP");
			message.setText("Your OTP is: " + otp);
			Transport.send(message);
			System.out.println("Email sent successfully!");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
