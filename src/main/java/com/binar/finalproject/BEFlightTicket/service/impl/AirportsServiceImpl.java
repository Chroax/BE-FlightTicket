package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.AirportRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirportResponse;
import com.binar.finalproject.BEFlightTicket.dto.CitiesResponse;
import com.binar.finalproject.BEFlightTicket.model.Airports;
import com.binar.finalproject.BEFlightTicket.model.Cities;
import com.binar.finalproject.BEFlightTicket.repository.AirportsRepository;
import com.binar.finalproject.BEFlightTicket.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportsServiceImpl implements AirportService {

    @Autowired
    private AirportsRepository airportsRepository;

    @Override
    public AirportResponse addAirports(AirportRequest airportRequest) {
        Airports airports = airportRequest.toAirports();
        try {
            airportsRepository.save(airports);
            return AirportResponse.build(airports);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<AirportResponse> searchAllAirports() {
        List<Airports> getAllAirports = airportsRepository.findAll();
        List<AirportResponse> allAirportsResponse = new ArrayList<>();
        for (Airports airports : getAllAirports) {
            AirportResponse airportResponse = AirportResponse.build(airports);
            allAirportsResponse.add(airportResponse);
        }
        return allAirportsResponse;
    }

    @Override
    public AirportResponse updateAirports(AirportRequest airportRequest, String airportName) {
        Airports airports = airportsRepository.findByAirportName(airportName);
        if (airports != null) {
            airports.setIataCode(airportRequest.getIataCode());
            airports.setAirportName(airportRequest.getAirportName());
            try {
                return AirportResponse.build(airports);
            } catch (Exception exception) {
                return null;
            }
        }
        else {
            throw new RuntimeException("Airport with name : " + airportName + " is not found");
        }
    }

    @Override
    public Boolean deleteAirportsByName(String airportName) {
        Airports airports = airportsRepository.findByAirportName(airportName);
        if(airports != null) {
            airportsRepository.deleteById(airports.getIataCode());
            return true;
        }
        else
            return false;
    }

    @Override
    public AirportResponse searchAirportsByName(String airportName) {
        Airports airports = airportsRepository.findByAirportName(airportName);
        if (airports != null){
            return AirportResponse.build(airports);
        }else {
            return null;
        }
    }
}
