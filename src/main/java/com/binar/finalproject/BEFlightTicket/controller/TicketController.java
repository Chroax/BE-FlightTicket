package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
}
