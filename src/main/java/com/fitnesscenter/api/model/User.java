package com.fitnesscenter.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "user", schema = "public")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@NotNull(message = "Email cannot be null!")
	private String email;
	@NotNull(message = "Password cannot be null!")
	private String password;
	@Column(name = "phonenumber")
	private String phoneNumber;
	private Integer isactive = 1;
	private LocalDateTime lastlogin = LocalDateTime.now();
	@Column(name = "islogged")
	private Integer isLogged;
	private String status;
}
