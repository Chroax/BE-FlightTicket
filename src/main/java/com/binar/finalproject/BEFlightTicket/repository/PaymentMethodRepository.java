package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.PaymentMethods;
import com.binar.finalproject.BEFlightTicket.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethods, Integer> {
    @Query("SELECT p FROM PaymentMethods p WHERE LOWER(p.paymentName) LIKE LOWER(:paymentName)")
    PaymentMethods findByName(@Param("paymentName") String paymentName);
}
