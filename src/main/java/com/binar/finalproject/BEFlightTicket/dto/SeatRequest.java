package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import com.binar.finalproject.BEFlightTicket.model.Seats;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SeatRequest {
    @NotEmpty(message = "Seat number is required.")
    private String seatNumber;
    @NotEmpty(message = "Seat type is required.")
    private String seatType;
    @NotEmpty(message = "Airplane name is required.")
    private String airplaneName;

    public Seats toSeats(Airplanes airplanes)
    {
        return Seats.builder()
                .seatNumber(this.seatNumber)
                .seatType(this.seatType)
                .airplanesSeats(airplanes)
                .build();
    }
}
