package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM orders o where o.userId = :userId")
    List<Orders> findAllOrderByUserId(@Param("userId") UUID userId);
    @Query(nativeQuery = true, value = "SELECT * FROM orders o where o.paymentId = :paymentId")
    List<Orders> findAllOrderByPaymentId(@Param("paymentId") Integer paymentId);
}
