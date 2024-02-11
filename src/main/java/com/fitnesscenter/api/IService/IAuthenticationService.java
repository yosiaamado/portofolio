package com.fitnesscenter.api.IService;

import org.springframework.http.ResponseEntity;

import com.fitnesscenter.api.model.Authentication;
import com.fitnesscenter.api.model.User;

public interface IAuthenticationService {
	boolean getToken(String token);

	Authentication updateExpiredDate(Authentication auth);

	ResponseEntity<User> getLogin(User user);

	ResponseEntity<User> postRegister(User user);

	void forgetPassword(String email);

	String forgetPasswordOTP(Long userid, Integer otp);

	void Logout(Long userid);
}
