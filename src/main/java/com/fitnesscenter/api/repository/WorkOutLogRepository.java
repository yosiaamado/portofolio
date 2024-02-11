package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.WorkOutLog;

@Repository
public interface WorkOutLogRepository extends JpaRepository<WorkOutLog, Long> {

	@Query("select count(a.subcriptionId) from WorkOutLog a where a.subcriptionId = :subsid")
	Long getCountBySubs(@Param("subsid") Long id);
}
