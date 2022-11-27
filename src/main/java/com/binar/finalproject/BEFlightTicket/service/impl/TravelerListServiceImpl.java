package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.TravelerListRequest;
import com.binar.finalproject.BEFlightTicket.dto.TravelerListResponse;
import com.binar.finalproject.BEFlightTicket.dto.TravelerListUpdateRequest;
import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.Roles;
import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import com.binar.finalproject.BEFlightTicket.model.Users;
import com.binar.finalproject.BEFlightTicket.repository.TravelerListRepository;
import com.binar.finalproject.BEFlightTicket.repository.UserRepository;
import com.binar.finalproject.BEFlightTicket.repository.CountriesRepository;
import com.binar.finalproject.BEFlightTicket.service.TravelerListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TravelerListServiceImpl implements TravelerListService {

    @Autowired
    TravelerListRepository travelerListRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CountriesRepository countriesRepository;

    @Override
    public TravelerListResponse registerTravelerList(TravelerListRequest userRequest) {
        try{
            Optional<Users> users = userRepository.findById(userRequest.getUserId());
            Optional<Countries> countries = countriesRepository.findById(userRequest.getCountryCode());

            if(users.isPresent())
            {
                if(countries.isPresent())
                {
                    TravelerList travelerList = userRequest.toTravelerList(users.get(), countries.get());

                    try {
                        travelerListRepository.save(travelerList);
                        return TravelerListResponse.build(travelerList);
                    }
                    catch(Exception exception)
                    {
                        return null;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }catch (Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<TravelerListResponse> searchAllTravelerList() {
        List<TravelerList> allTravelerList = travelerListRepository.findAll();
        List<TravelerListResponse> allTravelerListResponse = new ArrayList<>();
        for (TravelerList travelerList: allTravelerList) {
            TravelerListResponse travelerListResponse = TravelerListResponse.build(travelerList);
            allTravelerListResponse.add(travelerListResponse);
        }
        return allTravelerListResponse;
    }

    @Override
    public List<TravelerListResponse> searchAllUserTravelerList(UUID userId) {
        List<TravelerList> allTravelerList = travelerListRepository.findAllTravelerListByUser(userId);
        List<TravelerListResponse> allTravelerListResponse = new ArrayList<>();
        for (TravelerList travelerList: allTravelerList) {
            TravelerListResponse travelerListResponse = TravelerListResponse.build(travelerList);
            allTravelerListResponse.add(travelerListResponse);
        }
        return allTravelerListResponse;
    }

    @Override
    public TravelerListResponse updateTravelerList(TravelerListUpdateRequest travelerListUpdateRequest, UUID travelerId) {
        Optional<TravelerList> isTravelerList = travelerListRepository.findById(travelerId);
        String message = null;
        if (isTravelerList.isPresent()) {
            TravelerList travelerList = isTravelerList.get();
            travelerList.setType(travelerListUpdateRequest.getType());
            travelerList.setTitle(travelerListUpdateRequest.getTitle());
            travelerList.setFirstName(travelerListUpdateRequest.getFirstName());
            travelerList.setLastName(travelerListUpdateRequest.getLastName());
            travelerList.setBirthDate(travelerListUpdateRequest.getBirthDate());
            if (travelerListUpdateRequest.getCountryCode() != null)
            {
                Optional<Countries> countries = countriesRepository.findById(travelerListUpdateRequest.getCountryCode());
                if(countries.isPresent())
                    travelerList.setCountriesTravelerList(countries.get());
                else
                    message = "Countries with this code doesnt exist";
            }
            if(message != null)
                return null;
            else
            {
                travelerListRepository.saveAndFlush(travelerList);
                return TravelerListResponse.build(travelerList);
            }
        } else
            return null;
    }
}
