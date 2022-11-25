package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Seats;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {

    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;
    @NotEmpty(message = "Seat type is required.")
    private String seatType;

    public Seats toSeats()
    {
        return Seats.builder()
                .seatNumber(this.seatNumber)
                .seatType(this.seatType)
                .build();
    }
}
