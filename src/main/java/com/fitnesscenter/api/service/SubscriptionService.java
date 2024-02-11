package com.fitnesscenter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnesscenter.api.IService.ISubscriptionService;
import com.fitnesscenter.api.model.Services;
import com.fitnesscenter.api.model.Subscription;
import com.fitnesscenter.api.model.WorkOutLog;
import com.fitnesscenter.api.repository.ServiceRepository;
import com.fitnesscenter.api.repository.SubscriptionRepository;
import com.fitnesscenter.api.repository.WorkOutLogRepository;

@Service
public class SubscriptionService implements ISubscriptionService {

	@Autowired
	ServiceRepository serviceRepos;
	SubscriptionRepository subsRepos;
	WorkOutLogRepository wolRepos;

	@Override
	public List<Services> getService() {
		return serviceRepos.findAll();
	}

	@Override
	public Subscription createSubs(Subscription subs) {
		subs.setStatus(1);
		return subsRepos.save(subs);
	}

	@Override
	public String cancelSubs(Subscription subs) {
		subs.setStatus(0);
		subsRepos.save(subs);
		return "Subscription Cancelled";
	}

	@Override
	public String useMeeting(WorkOutLog woLog) {
		Optional<Subscription> optSubs = subsRepos.findById(woLog.getSubcriptionId());
		if (optSubs.isPresent()) {
			Subscription subs = optSubs.get();
			if (subs.getTotalMeeting() >= wolRepos.getCountBySubs(subs.getId()) && subs.getStatus() == 1) {
				wolRepos.save(woLog);
				return "Used";
			}
			else {
				if (subs.getStatus() == 0)
					return "Inactive Subscription";
				else
					return "Reached the total meeting";
			}
		}
		return "Invalid Subscription";
	}

}
