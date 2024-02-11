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
@Table(name = "subscription")
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "userid")
	private Long userId;
	@Column(name = "serviceid")
	private Long serviceId;
	@Column(name = "startdate")
	private LocalDateTime startDate;
	@Column(name = "enddate")
	private LocalDateTime endDate;
	@Column(name = "totalmeeting")
	private Integer totalMeeting;
	private Integer status;

}
