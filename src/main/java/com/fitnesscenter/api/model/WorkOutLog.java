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
@Table(name = "workout_log")
public class WorkOutLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "subcriptionid")
	private Long subcriptionId;
	@Column(name = "dayofweek")
	private Integer dayOfWeek;
	private Integer duration;
	@Column(name = "starttime")
	private LocalDateTime startTime;
}
