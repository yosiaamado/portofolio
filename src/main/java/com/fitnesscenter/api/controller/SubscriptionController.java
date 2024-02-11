package com.fitnesscenter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesscenter.api.IService.ISubscriptionService;
import com.fitnesscenter.api.model.Services;
import com.fitnesscenter.api.model.Subscription;
import com.fitnesscenter.api.model.WorkOutLog;

@RestController
public class SubscriptionController {
	
	@Autowired
	ISubscriptionService subsService;
	
	@GetMapping("/service")
	public List<Services> getService() {
		return subsService.getService();
	}
	
	@PostMapping("/subscribe")
	public Subscription createSubs(Subscription subs) {
		return subsService.createSubs(subs);
	}

	@PostMapping("/useMeeting")
	public String useMeeting(WorkOutLog woLog) {
		return subsService.useMeeting(woLog);
	}


}
