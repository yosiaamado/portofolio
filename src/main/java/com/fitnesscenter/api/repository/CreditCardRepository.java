package com.fitnesscenter.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

}
