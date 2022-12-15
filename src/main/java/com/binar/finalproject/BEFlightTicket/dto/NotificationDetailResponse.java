package com.binar.finalproject.BEFlightTicket.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NotificationDetailResponse {

    private Integer unreadCount;

    List<NotificationResponse> content;
}
