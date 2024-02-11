package com.fitnesscenter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesscenter.api.IService.IPaymentService;
import com.fitnesscenter.api.model.Payment;
import com.fitnesscenter.api.model.Services;

@RestController
public class PaymentController {
	
	@Autowired
	IPaymentService paymentService;
	
	@PostMapping("/payment/{userid}/{serviceid}")
	public Payment getUserPayment(@PathVariable Long userid, @RequestBody Services service) {
		return paymentService.getUserPayment(userid, service);
	}
	
	@PostMapping("/setPaid/{userid}/{otp}/{serviceid}")
	public Payment setPaid(@PathVariable Long userid, @PathVariable Integer otp, @PathVariable Long serviceid) {
		return paymentService.setPaid(userid, otp, serviceid);
	}
}
