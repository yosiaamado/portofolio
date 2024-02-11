package com.fitnesscenter.api.IService;

import java.util.List;

import com.fitnesscenter.api.model.Services;
import com.fitnesscenter.api.model.Subscription;
import com.fitnesscenter.api.model.WorkOutLog;

public interface ISubscriptionService {
	List<Services> getService();

	Subscription createSubs(Subscription subs);

	String useMeeting(WorkOutLog woLog);

	String cancelSubs(Subscription subs);
}
