package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Notification;
import com.binar.finalproject.BEFlightTicket.model.Users;
import lombok.Data;

@Data
public class NotificationRequest {

    private String title;

    private String content;

    public Notification toNotification(Users users) {
        Notification notification =  new Notification();
        notification.setTitle(this.title);
        notification.setContent(this.content);
        notification.setUser(users);
        return notification;

    }
}
