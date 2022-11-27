package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addTicket (@RequestBody TicketRequest ticketRequest)
    {
        MessageModel messageModel = new MessageModel();
        TicketResponse ticketResponse = ticketService.addTicket(ticketRequest);
        if(ticketResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add ticket");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new ticket");
            messageModel.setData(ticketResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllTicket()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<TicketResponse> ticketGet = ticketService.getAllTicket();
            messageModel.setMessage("Success get all ticket");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(ticketGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all ticket");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
