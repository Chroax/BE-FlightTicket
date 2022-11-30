package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.SeatRequest;
import com.binar.finalproject.BEFlightTicket.dto.SeatResponse;

import java.util.List;

public interface SeatService {
    SeatResponse addSeat(SeatRequest seatRequest);
    SeatResponse searchSeatById(Integer seatId);
    List<SeatResponse> getAllSeat();
    SeatResponse updateSeat(SeatRequest seatRequest, Integer seatId);
    List<SeatResponse> searchAirplaneSeat(String airplaneName);
}
