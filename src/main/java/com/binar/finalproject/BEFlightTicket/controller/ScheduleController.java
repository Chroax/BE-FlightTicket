package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSchedule (@RequestBody ScheduleRequest scheduleRequest)
    {
        MessageModel messageModel = new MessageModel();
        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);
        if(scheduleResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add schedule");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new schedule");
            messageModel.setData(scheduleResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<MessageModel> updateSchedule(@PathVariable UUID scheduleId, @RequestBody ScheduleRequest scheduleRequest) {
        MessageModel messageModel = new MessageModel();
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(scheduleRequest, scheduleId);

        if(scheduleResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update schedule with id : " + scheduleId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update schedule with id : " + scheduleId);
            messageModel.setData(scheduleResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
