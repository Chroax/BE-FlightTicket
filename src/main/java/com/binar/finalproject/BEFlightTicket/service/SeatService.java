package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.SeatRequest;
import com.binar.finalproject.BEFlightTicket.dto.SeatResponse;

import java.util.List;

public interface SeatService {
    SeatResponse addSeat(SeatRequest seatRequest);
    SeatResponse searchSeatBySeatNumber(String seatNumber);
    List<SeatResponse> getAllSeat();
    SeatResponse updateSeat(SeatRequest seatRequest, String seatNumber);
    Boolean deleteSeat(String seatNumber);
}
