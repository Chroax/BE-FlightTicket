package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.AirplanesRequest;
import com.binar.finalproject.BEFlightTicket.dto.AirplanesResponse;
import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import com.binar.finalproject.BEFlightTicket.repository.AirplanesRepository;
import com.binar.finalproject.BEFlightTicket.service.AirplanesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
        Optional<Airplanes> isAirplanes = airplanesRepository.findById(airplaneName);
        if (isAirplanes.isPresent()){
            Airplanes airplanes = isAirplanes.get();
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
            throw new RuntimeException("Airplane with name: "+airplaneName+" not found");
    }

    @Override
    public Boolean deleteAirplane(String airplaneName) {
        Optional<Airplanes> isAirplanes = airplanesRepository.findById(airplaneName);
        if (isAirplanes.isPresent()){
            Airplanes airplanes = isAirplanes.get();
            airplanesRepository.deleteById(airplanes.getAirplaneName());
            return true;
        }
        return null;
    }
}
