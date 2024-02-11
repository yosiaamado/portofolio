package com.fitnesscenter.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "userid")
	private Long userId;
	@Column(name = "serviceid")
	private Long serviceId;
	private float amount;
	private Integer otp;
	@Column(name = "paymentstatus")
	private Integer paymentStatus;
	@Column(name = "expireddate")
	private LocalDateTime expiredDate;
	@Column(name = "paymentdate")
	private LocalDateTime paymentDate;
}
