package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.MessageModel;
import com.binar.finalproject.BEFlightTicket.dto.NotificationDetailResponse;
import com.binar.finalproject.BEFlightTicket.dto.NotificationRequest;
import com.binar.finalproject.BEFlightTicket.dto.NotificationResponse;
import com.binar.finalproject.BEFlightTicket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @ResponseBody
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> addNotification(@RequestBody NotificationRequest notificationRequest, @RequestParam UUID userId) {
        MessageModel messageModel =  new MessageModel();
        NotificationResponse notificationResponse = notificationService.addNotification(notificationRequest, userId);
        if (notificationResponse == null) {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("success new notification");
            messageModel.setData(notificationRequest);
            return ResponseEntity.ok().body(messageModel);
        }else {
            messageModel.setMessage("Failed notification user");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }

    @ResponseBody
    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_BUYER')")
    public ResponseEntity<MessageModel> getAll(@RequestParam UUID userId) {
        MessageModel messageModel = new MessageModel();
        NotificationDetailResponse notificationDetailResponse = notificationService.getAllNotificationByUserId(userId);
        if (notificationDetailResponse == null) {
            messageModel.setMessage("Failed notification user");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success get All Notification By userId");
            messageModel.setData(notificationDetailResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @ResponseBody
    @PutMapping("/update-status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<MessageModel> updateReadStatus(@RequestParam UUID userId, @RequestParam UUID notificationId) {
        MessageModel messageModel = new MessageModel();
        NotificationResponse notificationDetailResponse = notificationService.updateIsRead(userId, notificationId);
        if (notificationDetailResponse == null) {
            messageModel.setMessage("Failed notification user");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        } else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update read user notification");
            messageModel.setData(notificationDetailResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
    
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> updateNotification(@RequestBody NotificationRequest notificationRequest, @RequestParam UUID notificationId, @RequestParam UUID userId)
    {
        MessageModel messageModel = new MessageModel();
        NotificationResponse notificationResponse = notificationService.updateNotification(notificationRequest, notificationId, userId);
        if(notificationResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update notification");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update notification with title : " + notificationResponse.getTitle());
            messageModel.setData(notificationResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
