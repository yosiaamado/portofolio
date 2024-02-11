package com.fitnesscenter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesscenter.api.IService.IAuthenticationService;
import com.fitnesscenter.api.model.User;

@RestController
public class AuthenticationController {
	
	@Autowired
	private IAuthenticationService authService;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		return authService.getLogin(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		return authService.postRegister(user);
	}

	@PostMapping("/forgetpassword/{email}")
	public void forgetPassword(@PathVariable String email) {
		authService.forgetPassword(email);
	}

	@PostMapping("/forgetpasswordotp/{userid}/{otp}")
	public String forgetPasswordOTP(@PathVariable Long userid, @PathVariable Integer otp) {
		return authService.forgetPasswordOTP(userid, otp);
	}

	@PostMapping("/logout/{userid}")
	public void Logout(@PathVariable Long userid) {
		authService.Logout(userid);
	}
}
