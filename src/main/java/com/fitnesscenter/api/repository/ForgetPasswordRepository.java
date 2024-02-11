package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.ForgetPassword;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Long> {

	@Query("SELECT a FROM ForgetPassword a WHERE a.userId = :userid AND expiredDate > CURRENT_TIMESTAMP")
	ForgetPassword getByUserId(Long userid);
}
