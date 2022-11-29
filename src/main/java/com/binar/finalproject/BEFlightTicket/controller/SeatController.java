package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSeat (@RequestBody SeatRequest seatRequest)
    {
        MessageModel messageModel = new MessageModel();
        SeatResponse seatResponse = seatService.addSeat(seatRequest);
        if(seatResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add seat");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new seat");
            messageModel.setData(seatResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/number/{seatNumber}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> getSeatByNumber(@PathVariable String seatNumber){
        MessageModel messageModel = new MessageModel();
        try {
            SeatResponse seatGet = seatService.searchSeatBySeatNumber(seatNumber);
            messageModel.setMessage("Success get seat");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(seatGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get seat");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('BUYER')")
    public ResponseEntity<MessageModel> getAllSeats()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<SeatResponse> seatGet = seatService.getAllSeat();
            messageModel.setMessage("Success get all seats");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(seatGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all seats");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/get-all/airplane/{airplaneName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> getAirplaneSeat(@PathVariable String airplaneName){
        MessageModel messageModel = new MessageModel();
        try {
            List<SeatResponse> seatResponses = seatService.searchAirplaneSeat(airplaneName);
            messageModel.setMessage("Success get seat by airplane name : " + airplaneName);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(seatResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get seat by airplane name, " + airplaneName + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PutMapping(value = "/update/{seatNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> updateSeat(@PathVariable String seatNumber, @RequestBody SeatRequest seatRequest)
    {
        MessageModel messageModel = new MessageModel();
        SeatResponse seatResponse = seatService.updateSeat(seatRequest, seatNumber);

        if(seatResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update seat");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update seat with number: " + seatResponse.getSeatId());
            messageModel.setData(seatResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

}
