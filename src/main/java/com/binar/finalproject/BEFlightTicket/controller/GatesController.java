package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.GatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gates")
public class GatesController {

    @Autowired
    private GatesService gatesService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createGates(@RequestBody GatesRequest gatesRequest) {
        MessageModel messageModel = new MessageModel();

        GatesResponse gatesResponse = gatesService.addGates(gatesRequest);
        if(gatesResponse == null) {
            messageModel.setMessage("Failed to create Gates");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Create new Gates");
            messageModel.setData(gatesResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageModel> getAllGates() {
        MessageModel messageModel = new MessageModel();
        try {
            List<GatesResponse> getAllGates = gatesService.searchAllGates();
            messageModel.setMessage("Success get all Gates");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAllGates);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception) {
            messageModel.setMessage("Failed get all Gates");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
