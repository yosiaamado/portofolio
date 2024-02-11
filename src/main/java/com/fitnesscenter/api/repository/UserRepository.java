package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT a FROM User a WHERE a.email = :email")
	User getByEmail(@Param("email") String email);
}
