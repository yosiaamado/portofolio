package com.fitnesscenter.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fitnesscenter.api.IService.IUserService;
import com.fitnesscenter.api.model.User;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private IUserService eService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return eService.getUsers();
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		return eService.getById(id);
	}

	@PostMapping("/user")
	public User saveUser(@Valid @RequestBody User user) {
		return eService.saveUser(user);
	}

	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		user.setId(id);
		return eService.updateUser(user);
	}
}
