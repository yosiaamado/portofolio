package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.Services;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

}
