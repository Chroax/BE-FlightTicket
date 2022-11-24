package com.binar.finalproject.BEFlightTicket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.AirplanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    @Autowired
    private AirplanesService airplanesService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addAirplane(@RequestBody AirplanesRequest airplanesRequest)
    {
        MessageModel messageModel = new MessageModel();
        AirplanesResponse airplanesResponse = airplanesService.insertAirplane(airplanesRequest);
        if(airplanesResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add airplane");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Add new airplane");
            messageModel.setData(airplanesResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllAirplane()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<AirplanesResponse> airplaneGet = airplanesService.getAllAirplane();
            messageModel.setMessage("Success get all airplane");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(airplaneGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all airplane");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
