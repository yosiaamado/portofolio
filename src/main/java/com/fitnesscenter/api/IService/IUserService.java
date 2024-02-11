package com.fitnesscenter.api.IService;

import java.util.List;

import com.fitnesscenter.api.model.CreditCard;
import com.fitnesscenter.api.model.User;

public interface IUserService {
	List<User> getUsers();

	User getById(Long Id);

	User getByEmail(String email);

	User saveUser(User user);

	User updateUser(User user);

	String resetPassword(User user);

	CreditCard saveCC(CreditCard cc);
}
