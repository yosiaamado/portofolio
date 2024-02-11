package com.fitnesscenter.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fitnesscenter.api.IService.IUserService;
import com.fitnesscenter.api.model.CreditCard;
import com.fitnesscenter.api.model.User;
import com.fitnesscenter.api.repository.CreditCardRepository;
import com.fitnesscenter.api.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	private CreditCardRepository ccRepos;

	@Override
	@Transactional(readOnly = false)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Long Id) {
		Optional<User> usr = userRepository.findById(Id);
		if (usr.isPresent()) {
			return usr.get();
		}
		throw new RuntimeException("User not found " + Id);
	}

	@Override
	public User getByEmail(String email) {
		User usr = userRepository.getByEmail(email);
		if (usr != null) {
			return usr;
		}
		throw new RuntimeException("User not found " + email);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String resetPassword(User user) {
		Optional<User> optUsr = userRepository.findById(user.getId());
		if (optUsr.isPresent()) {
			User usr = optUsr.get();
			if (user.getPassword() == usr.getPassword())
				return "Can't use the same password";
			else {
				usr.setPassword(user.getPassword());
				userRepository.save(usr);
				return "PasswordChange";
			}
		}
		else
			return "User not found";
	}

	@Override
	public CreditCard saveCC(CreditCard cc) {
		CreditCard cCard = ccRepos.save(cc);
		cCard.setCardNumber(maskSensitiveData(cc.getCardNumber(), 4));
		cCard.setCvv(maskSensitiveData(cc.getCvv(), 1));
		return ccRepos.save(cCard);
	}

	public static String maskSensitiveData(String data, int visibleDigits) {
		int visibleCharacters = visibleDigits;
		int maskedLength = data.length() - visibleCharacters;

		StringBuilder maskedData = new StringBuilder();
		maskedData.append(data, 0, visibleCharacters);
		for (int i = 0; i < maskedLength; i++) {
			maskedData.append('*');
		}
		return maskedData.toString();
	}
}
