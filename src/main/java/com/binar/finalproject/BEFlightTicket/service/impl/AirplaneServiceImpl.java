package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.AirplanesRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirplanesResponse;
import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import com.binar.finalproject.BEFlightTicket.repository.AirplanesRepository;
import com.binar.finalproject.BEFlightTicket.service.AirplanesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AirplaneServiceImpl implements AirplanesService {

    @Autowired
    private AirplanesRepository airplanesRepository;

    @Override
    public AirplanesResponse insertAirplane(AirplanesRequest airplanesRequest) {
        Airplanes airplanes = airplanesRequest.toAirplanes();
        try {
            airplanesRepository.save(airplanes);
            return AirplanesResponse.build(airplanes);
        }
        catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<AirplanesResponse> getAllAirplane() {
        List<Airplanes> allAirplane = airplanesRepository.findAll();
        List<AirplanesResponse> allAirplaneResponse = new ArrayList<>();
        for (Airplanes airplanes : allAirplane){
            AirplanesResponse airplanesResponse = AirplanesResponse.build(airplanes);
            allAirplaneResponse.add(airplanesResponse);
        }
        return allAirplaneResponse;
    }

    @Override
    public AirplanesResponse updateAirplane(AirplanesRequest airplanesRequest, String airplaneName) {
        Airplanes airplanes = airplanesRepository.findByName(airplaneName);
        if (airplanes != null){
            airplanes.setAirplaneName(airplanesRequest.getAirplaneName());
            try {
                airplanesRepository.save(airplanes);
                return AirplanesResponse.build(airplanes);
            }
            catch (Exception exception){
                return null;
            }
        }
        else
            throw new RuntimeException("Roles with name: "+airplaneName+" not found");
    }

    @Override
    public Boolean deleteAirplane(String airplaneName) {
        Airplanes airplanes = airplanesRepository.findByName(airplaneName);
        if (airplanes != null){
            airplanesRepository.deleteById(airplanes.getAirplaneName());
            return true;
        }
        return null;
    }
}
