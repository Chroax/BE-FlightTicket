package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.TravelerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traveler-list")
public class TravelerListController {

    @Autowired
    private TravelerListService travelerListService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerUser(@RequestBody TravelerListRequest travelerListRequest) {
        MessageModel messageModel = new MessageModel();

        TravelerListResponse travelerListResponse = travelerListService.registerTravelerList(travelerListRequest);
        if(travelerListResponse == null)
        {
            messageModel.setMessage("Failed register new traveler list");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new traveler list");
            messageModel.setData(travelerListResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
