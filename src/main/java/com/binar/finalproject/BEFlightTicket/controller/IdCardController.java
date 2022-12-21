package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.IdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/id-card")
public class IdCardController {
    @Autowired
    IdCardService idCardService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerIdCard(@RequestBody IdCardRequest idCardRequest) {
        MessageModel messageModel = new MessageModel();

        IdCardResponse idCardResponse = idCardService.registerIdCard(idCardRequest);
        if(idCardResponse == null)
        {
            messageModel.setMessage("Failed register new id-card");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new id-card");
            messageModel.setData(idCardResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/get-all/traveler/{travelerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> getTravelerIdCard(@PathVariable UUID travelerId){
        MessageModel messageModel = new MessageModel();
        try {
            IdCardResponse idCardResponses = idCardService.searchTravelerListIdCard(travelerId);
            messageModel.setMessage("Success get id-card by traveler id : " + travelerId);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(idCardResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get id-card by traveler id, " + travelerId + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    @GetMapping("/get/{idCardId}")
    public ResponseEntity<MessageModel> getIdCard(@PathVariable UUID idCardId){
        MessageModel messageModel = new MessageModel();
        try {
            IdCardResponse idCardResponses = idCardService.searchIdCard(idCardId);
            messageModel.setMessage("Success get id-card");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(idCardResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get id-card");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    
    @PutMapping("/update/{idCardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> updateIdCard(@PathVariable UUID idCardId, @RequestBody IdCardRequest idCardRequest) {
        MessageModel messageModel = new MessageModel();
        IdCardResponse idCardResponse = idCardService.updateIdCard(idCardRequest, idCardId);

        if(idCardResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update id-card with id : " + idCardId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update id-card with id : " + idCardId);
            messageModel.setData(idCardResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
