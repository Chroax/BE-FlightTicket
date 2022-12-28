package com.binar.finalproject.BEFlightTicket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @GetMapping("/findby-name")
    public ResponseEntity<MessageModel> getAirplaneByName(@RequestParam String airplaneName){
        MessageModel messageModel = new MessageModel();
        try {
            AirplanesResponse airplaneGet = airplanesService.searchAirplaneByName(airplaneName);
            messageModel.setMessage("Success get airplane");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(airplaneGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get airplane");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
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

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> updateAirplane(@RequestParam String airplaneName, @RequestBody AirplanesRequest airplanesRequest)
    {
        MessageModel messageModel = new MessageModel();
        AirplanesResponse airplanesResponse = airplanesService.updateAirplane(airplanesRequest, airplaneName);

        if(airplanesResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update airplane");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update airplane with name : " + airplanesResponse.getAirplaneName());
            messageModel.setData(airplanesResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> deleteAirplane(@RequestParam String airplaneName)
    {
        MessageModel messageModel = new MessageModel();
        Boolean deleteAirplane = airplanesService.deleteAirplane(airplaneName);
        if(deleteAirplane)
        {
            messageModel.setMessage("Success delete airplane by name: " + airplaneName);
            messageModel.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(messageModel);
        }
        else
        {
            messageModel.setMessage("Failed delete airplane by name: " + airplaneName + ", not found");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }
}
