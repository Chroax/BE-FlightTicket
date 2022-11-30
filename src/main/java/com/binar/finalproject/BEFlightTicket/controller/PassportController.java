package com.binar.finalproject.BEFlightTicket.controller;


import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/passport")
public class PassportController {
    @Autowired
    PassportService passportService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('BUYER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerPassport(@RequestBody PassportRequest passportRequest) {
        MessageModel messageModel = new MessageModel();

        PassportResponse passportResponse = passportService.registerPassport(passportRequest);
        if(passportResponse == null)
        {
            messageModel.setMessage("Failed register new passport");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new passport");
            messageModel.setData(passportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/get-all/traveler/{travelerId}")
    @PreAuthorize("hasAnyRole('BUYER')")
    public ResponseEntity<MessageModel> getTravelerPassport(@PathVariable UUID travelerId){
        MessageModel messageModel = new MessageModel();
        try {
            PassportResponse passportResponse = passportService.searchTravelerListPassport(travelerId);
            messageModel.setMessage("Success get passport by traveler id : " + travelerId);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(passportResponse);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get passport by traveler id, " + travelerId + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PreAuthorize("hasAnyRole('BUYER')")
    @GetMapping("/get/{passportId}")
    public ResponseEntity<MessageModel> getPassport(@PathVariable UUID passportId){
        MessageModel messageModel = new MessageModel();
        try {
            PassportResponse passportResponse = passportService.searchPassport(passportId);
            messageModel.setMessage("Success get passport");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(passportResponse);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get passport");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PutMapping("/update/{passportId}")
    @PreAuthorize("hasAnyRole('BUYER')")
    public ResponseEntity<MessageModel> updatePassport(@PathVariable UUID passportId, @RequestBody PassportRequest passportRequest) {
        MessageModel messageModel = new MessageModel();
        PassportResponse passportResponse = passportService.updatePassport(passportRequest, passportId);

        if(passportResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update passport with id : " + passportId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update passport with id : " + passportId);
            messageModel.setData(passportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
