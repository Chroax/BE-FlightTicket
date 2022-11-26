package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/add")
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





}
