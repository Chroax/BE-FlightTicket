package com.binar.finalproject.BEFlightTicket.dummy;

import com.binar.finalproject.BEFlightTicket.model.Airports;

import java.util.List;

public class DataDummyAirport {

    public static List<Airports> getAirport(){
        Airports airports = new Airports();
        Airports airports1 = new Airports();
        airports.setIataCode("SMG");
        airports.setAirportName("ahmad yani");
        airports1.setIataCode("BBI");
        airports1.setAirportName("ngurah rai");
        return List.of(airports, airports1);
    }
}
