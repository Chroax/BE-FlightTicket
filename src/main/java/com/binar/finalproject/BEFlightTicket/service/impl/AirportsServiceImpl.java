package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.AirportRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirportResponse;
import com.binar.finalproject.BEFlightTicket.dto.CitiesResponse;
import com.binar.finalproject.BEFlightTicket.dto.CountriesResponse;
import com.binar.finalproject.BEFlightTicket.model.Airports;
import com.binar.finalproject.BEFlightTicket.model.Cities;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.repository.AirportsRepository;
import com.binar.finalproject.BEFlightTicket.repository.CitiesRepository;
import com.binar.finalproject.BEFlightTicket.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportsServiceImpl implements AirportService {

    @Autowired
    private AirportsRepository airportsRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public AirportResponse addAirports(AirportRequest airportRequest) {
        try {
            Optional<Cities> cities = citiesRepository.findById(airportRequest.getCityCode());
                if(cities.isPresent())
                {
                    Airports airports = Airports.builder()
                            .iataCode(airportRequest.getIataCode())
                            .airportName(airportRequest.getAirportName())
                            .citiesAirport(cities.get())
                            .build();
                    airportsRepository.save(airports);
                    return AirportResponse.build(airports);
                }
                else
                    return null;
        }
        catch (Exception ignore){
            return null;
        }
//        Airports airports = airportRequest.toAirports();
//        try {
//            airportsRepository.save(airports);
//            return AirportResponse.build(airports);
//        }
//        catch(Exception exception)
//        {
//            return null;
//        }
    }

    @Override
    public List<AirportResponse> searchAllAirports() {
        List<Airports> allAirports = airportsRepository.findAll();
        List<AirportResponse> allAirportResponse = new ArrayList<>();
        for (Airports airports : allAirports) {
            AirportResponse airportResponse = AirportResponse.build(airports);
            allAirportResponse.add(airportResponse);
        }
        return allAirportResponse;
    }

    @Override
    public AirportResponse updateAirports(AirportRequest airportRequest, String iataCode) {
        Optional<Airports> isAirport = airportsRepository.findById(iataCode);
        String message = null;
        if (isAirport.isPresent()) {
            Airports airports = isAirport.get();
            airports.setIataCode(airportRequest.getIataCode());
            airports.setAirportName(airportRequest.getAirportName());
            if (airportRequest.getCityCode() != null)
            {
                Optional<Cities> cities = citiesRepository.findById(airportRequest.getCityCode());
                if(cities.isPresent())
                    airports.setCitiesAirport(cities.get());
                else
                    message = "City with this code doesnt exist";
            }
            if(message != null)
                return null;
            else
            {
                airportsRepository.saveAndFlush(airports);
                return AirportResponse.build(airports);
            }
        } else
            return null;
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
