package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

	@Query("SELECT a FROM Authentication a WHERE a.token = ':token'")
	Authentication findByToken(@Param("token") String token);

	@Query("SELECT a FROM Authentication a WHERE a.userId = :userid")
	Authentication findByUserId(@Param("userid") Long id);
}
