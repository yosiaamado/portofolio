package com.fitnesscenter.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "forget_password")
public class ForgetPassword {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "userid")
	private Long userId;
	private Integer otp;
	@Column(name = "expireddate")
	private LocalDateTime expiredDate;
}
