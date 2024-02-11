package com.fitnesscenter.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fitnesscenter.api.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT a FROM Payment a WHERE a.userId = :userid and a.serviceId = :serviceid and a.paymentStatus = 0")
	List<Payment> findByUserId(@Param("userid") Long id, @Param("serviceid") Long serviceId);

	@Query("SELECT a FROM Payment a WHERE a.userId = :userid and a.serviceId = :serviceid and a.paymentStatus = 0")
	Payment findPaymentUser(@Param("userid") Long id, @Param("serviceid") Long serviceId);
}
