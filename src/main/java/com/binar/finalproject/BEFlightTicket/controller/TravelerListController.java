package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.TravelerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<MessageModel> getAllUsers(@PathVariable UUID userId){
        MessageModel messageModel = new MessageModel();
        try {
            List<TravelerListResponse> travelerListGet = travelerListService.searchAllUserTravelerList(userId);
            messageModel.setMessage("Success get all traveler list by userId : " + userId);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(travelerListGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all traveler list by userId, " + userId + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
