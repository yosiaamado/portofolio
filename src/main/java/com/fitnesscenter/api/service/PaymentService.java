package com.fitnesscenter.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesscenter.api.IService.IPaymentService;
import com.fitnesscenter.api.IService.ISubscriptionService;
import com.fitnesscenter.api.IService.IUserService;
import com.fitnesscenter.api.model.Payment;
import com.fitnesscenter.api.model.Services;
import com.fitnesscenter.api.model.Subscription;
import com.fitnesscenter.api.model.User;
import com.fitnesscenter.api.repository.PaymentRepository;
import com.fitnesscenter.api.repository.ServiceRepository;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	PaymentRepository paymentRepos;
	ServiceRepository servRepos;
	ISubscriptionService subsService;
	IUserService usrService;

	@Override
	public Payment getUserPayment(Long userid, Services service) {
		List<Payment> existPayment = paymentRepos.findByUserId(userid, service.getId());
		if(existPayment.size() < 1) {
			Payment payment = new Payment();
			User usr = usrService.getById(userid);
			payment.setUserId(userid);
			payment.setServiceId(service.getId());
			payment.setAmount(service.getMeetingPrice());
			Integer otp = AuthenticationService.generateOTP();
			payment.setOtp(otp);
			payment.setPaymentStatus(1);
			payment.setExpiredDate(LocalDateTime.now().plusMinutes(10));
			paymentRepos.save(payment);
			
			if (AuthenticationService.sendOTP(usr.getEmail(), otp)) {
				payment.setOtp(null);
				return payment;
			}
			else
				return null;
		}
		else
			return null;
	}

	@Override
	public Payment setPaid(Long userid, Integer otp, Long serviceid) {
		Payment payment = paymentRepos.findPaymentUser(userid, serviceid);
		if (payment.getOtp() == otp && payment.getExpiredDate().isBefore(LocalDateTime.now())) {
			payment.setPaymentStatus(2);
			payment.setPaymentDate(LocalDateTime.now());
			paymentRepos.save(payment);

			Optional<Services> existService = servRepos.findById(serviceid);
			if(existService.isPresent()) {
				Services service = existService.get();
				Subscription subs = new Subscription();
				subs.setUserId(userid);
				subs.setServiceId(serviceid);
				subs.setStartDate(LocalDateTime.now());
				subs.setTotalMeeting(service.getTotalMeeting());
				subs.setStatus(1);
				subsService.createSubs(subs);
				return payment;
			}
		}
		return null;
	}
}
