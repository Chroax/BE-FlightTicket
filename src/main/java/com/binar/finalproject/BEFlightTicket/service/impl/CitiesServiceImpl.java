package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.CitiesRequest;
import com.binar.finalproject.BEFlightTicket.dto.CitiesResponse;
import com.binar.finalproject.BEFlightTicket.model.Cities;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.repository.CitiesRepository;
import com.binar.finalproject.BEFlightTicket.repository.CountriesRepository;
import com.binar.finalproject.BEFlightTicket.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private CountriesRepository countriesRepository;

    @Override
    public CitiesResponse addCity(CitiesRequest citiesRequest) {
        try {
            Optional<Countries> countries = countriesRepository.findById(citiesRequest.getCountryCode());
            if(countries.isPresent())
            {
                Cities cities = Cities.builder()
                        .cityCode(citiesRequest.getCityCode())
                        .cityName(citiesRequest.getCityName())
                        .countriesCities(countries.get())
                        .build();
                citiesRepository.saveAndFlush(cities);
                return CitiesResponse.build(cities);
            }
            else
                return null;
        }
        catch (Exception ignore){
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
        Cities isCity = citiesRepository.findByCityName(cityName);
        String message = null;
        if (isCity != null){
//            Cities cities = isCity;
            isCity.setCityCode(citiesRequest.getCityCode());
            isCity.setCityName(citiesRequest.getCityName());
            if (citiesRequest.getCountryCode() != null){
                Optional<Countries> countries = countriesRepository.findById(citiesRequest.getCountryCode());
                if(countries.isPresent()){
                    isCity.setCountriesCities(countries.get());
                }else {
                    message = "Countries with this code doesnt exist";
                }
            }
            if (message != null) {
                return null;
            }
            else {
                citiesRepository.saveAndFlush(isCity);
                return CitiesResponse.build(isCity);
            }
        }else {
            return null;
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
