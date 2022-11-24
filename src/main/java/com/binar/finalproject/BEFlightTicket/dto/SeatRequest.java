package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Seats;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {
    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;

    public Seats toSeats()
    {
        return Seats.builder()
                .seatNumber(this.seatNumber)
                .build();
    }
}
