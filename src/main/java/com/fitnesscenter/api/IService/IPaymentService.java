package com.fitnesscenter.api.IService;

import com.fitnesscenter.api.model.Payment;
import com.fitnesscenter.api.model.Services;

public interface IPaymentService {
	Payment getUserPayment(Long userid, Services service);

	Payment setPaid(Long userid, Integer otp, Long serviceid);
}
