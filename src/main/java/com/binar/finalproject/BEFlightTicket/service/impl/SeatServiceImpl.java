package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.SeatRequest;
import com.binar.finalproject.BEFlightTicket.dto.SeatResponse;
import com.binar.finalproject.BEFlightTicket.model.Seats;
import com.binar.finalproject.BEFlightTicket.repository.SeatRepository;
import com.binar.finalproject.BEFlightTicket.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public SeatResponse addSeat(SeatRequest seatRequest) {
        Seats seats = seatRequest.toSeats();
        try
        {
            seatRepository.save(seats);
            return SeatResponse.build(seats);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public SeatResponse searchSeatBySeatNumber(String seatNumber) {
        Seats seats = seatRepository.findByNumber(seatNumber);
        if (seats != null)
            return SeatResponse.build(seats);
        else
            return null;
    }

    @Override
    public List<SeatResponse> getAllSeat() {
        List<Seats> allSeat = seatRepository.findAll();
        List<SeatResponse> allSeatResponse = new ArrayList<>();
        for (Seats seats : allSeat)
        {
            SeatResponse seatResponse = SeatResponse.build(seats);
            allSeatResponse.add(seatResponse);
        }
        return allSeatResponse;
    }

    @Override
    public SeatResponse updateSeat(SeatRequest seatRequest, String seatNumber) {
        Seats seats = seatRepository.findByNumber(seatNumber);
        String message = null;
        if (seats != null)
        {
            if (seatRequest.getSeatNumber() != null)
                seats.setSeatNumber(seatRequest.getSeatNumber());
            else
                message = "Seat with number: "+seatNumber+" not found";
            if (seatRequest.getSeatType() != null)
                seats.setSeatType(seatRequest.getSeatType());
            if (message == null)
                return null;
            else
            {
                seatRepository.save(seats);
                return SeatResponse.build(seats);
            }
        }
        else
            return null;
    }

    @Override
    public Boolean deleteSeat(String seatNumber) {
        Seats seats = seatRepository.findByNumber(seatNumber);
        if (seats != null)
        {
            seatRepository.deleteById(seats.getSeatId());
            return true;
        }
        return false;
    }
}
