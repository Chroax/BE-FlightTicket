package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.CountriesRequest;
import com.binar.finalproject.BEFlightTicket.dto.CountriesResponse;
import com.binar.finalproject.BEFlightTicket.dto.RoleRequest;
import com.binar.finalproject.BEFlightTicket.dto.RoleResponse;

import java.util.List;

public interface CountriesService {

    CountriesResponse addCountries(CountriesRequest countriesRequest);
    List<CountriesResponse> searchAllCountries();
    CountriesResponse updateCountries(CountriesRequest countriesRequest, String countryName);
    Boolean deleteCountries(String countryName);
}
