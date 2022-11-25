package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.CitiesRequest;
import com.binar.finalproject.BEFlightTicket.dto.CitiesResponse;
import com.binar.finalproject.BEFlightTicket.model.Cities;
import com.binar.finalproject.BEFlightTicket.repository.CitiesRepository;
import com.binar.finalproject.BEFlightTicket.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesRepository citiesRepository;

    @Override
    public CitiesResponse addCity(CitiesRequest citiesRequest) {
        Cities cities = citiesRequest.toCities();
        try {
            citiesRepository.save(cities);
            return CitiesResponse.build(cities);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<CitiesResponse> searchAllCity() {
        List<Cities> getAllCity = citiesRepository.findAll();
        List<CitiesResponse> allCityResponse = new ArrayList<>();
        for (Cities cities : getAllCity) {
            CitiesResponse citiesResponse = CitiesResponse.build(cities);
            allCityResponse.add(citiesResponse);
        }
        return allCityResponse;
    }

    @Override
    public CitiesResponse updateCity(CitiesRequest citiesRequest, String cityName) {
        Cities cities = citiesRepository.findByCityName(cityName);
        if (cities != null) {
            cities.setCityCode(citiesRequest.getCityCode());
            cities.setCityName(citiesRequest.getCityName());
            try {
                return CitiesResponse.build(cities);
            } catch (Exception exception) {
                return null;
            }
        }
        else {
            throw new RuntimeException("City with name : " + cityName + " is not found");
        }
    }

    @Override
    public Boolean deleteCity(String cityName) {
        Cities cities = citiesRepository.findByCityName(cityName);
        if(cities != null) {
            citiesRepository.deleteById(cities.getCityCode());
            return true;
        }
        else
            return false;
    }

    @Override
    public CitiesResponse searchCityByName(String cityName) {
        Cities cities = citiesRepository.findByCityName(cityName);
        if (cities != null){
            return CitiesResponse.build(cities);
        }else {
            return null;
        }
    }
}
