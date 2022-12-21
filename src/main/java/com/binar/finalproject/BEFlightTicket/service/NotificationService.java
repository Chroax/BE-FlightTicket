package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.NotificationDetailResponse;
import com.binar.finalproject.BEFlightTicket.dto.NotificationRequest;
import com.binar.finalproject.BEFlightTicket.dto.NotificationResponse;

import java.util.UUID;

public interface NotificationService {

    NotificationDetailResponse getAllNotificationByUserId(UUID userId);

    NotificationResponse updateIsRead(UUID userId, UUID notificationId);

    NotificationResponse addNotification(NotificationRequest notificationRequest, UUID userId);

    NotificationResponse updateNotification(NotificationRequest notificationRequest, UUID notificationId, UUID userId);

}
