package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketResponse addTicket(TicketRequest ticketRequest);
    List<TicketResponse> getAllTicket();
    TicketResponse updateTicket(TicketRequest ticketRequest, UUID ticketId);
    TicketResponse searchTicketById(UUID ticketId);
    List<TicketResponse> searchOrderTicket (UUID orderId);
}
