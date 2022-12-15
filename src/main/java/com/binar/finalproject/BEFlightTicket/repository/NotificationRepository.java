package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM notification\n" + "WHERE user_id = :userId")
    List<Notification> getAllNotificationByUserId(@Param("userId") UUID userId);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM notification WHERE is_read = false")
    Integer getUnreadNotification();
}
