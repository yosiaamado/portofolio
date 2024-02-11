package com.fitnesscenter.api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "credit_card")
public class CreditCard {
	@Id
	@Column(name = "cardnumber")
	@NotNull(message = "Card Number cannot be null!")
	private String cardNumber;
	@Column(name = "userid")
	@NotNull(message = "Card Number cannot be null!")
	private Long userId;
	private String cvv;
	@Column(name = "expireddaate")
	private LocalDate expiredDate;
	@Column(name = "cardholder")
	@NotNull(message = "Card Number cannot be null!")
	private String cardHolder;
}
