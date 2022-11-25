package com.binar.finalproject.BEFlightTicket.controller;


import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    PassportService passportService;

    @PostMapping("/add")
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
}
