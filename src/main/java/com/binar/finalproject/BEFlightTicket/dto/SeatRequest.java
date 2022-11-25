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
    private String airplane_name;
    public Seats toSeats(Airplanes airplanes)
    {
        Seats seats = new Seats();
        seats.setSeatNumber(this.seatNumber);
        seats.setSeatType(this.seatType);
        seats.setAirplanesSeats(airplanes);
        return seats;
    }
}
