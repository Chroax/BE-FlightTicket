package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.IDCardRequest;
import com.binar.finalproject.BEFlightTicket.dto.IDCardResponse;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.IDCard;
import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import com.binar.finalproject.BEFlightTicket.repository.CountriesRepository;
import com.binar.finalproject.BEFlightTicket.repository.IdCardRepository;
import com.binar.finalproject.BEFlightTicket.repository.TravelerListRepository;
import com.binar.finalproject.BEFlightTicket.service.IdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IdCardServiceImpl implements IdCardService {
    @Autowired
    IdCardRepository idCardRepository;
    @Autowired
    TravelerListRepository travelerListRepository;
    @Autowired
    CountriesRepository countriesRepository;


    @Override
    public IDCardResponse registerIdCard(IDCardRequest idCardRequest) {
        try{
            Optional<TravelerList> travelerList = travelerListRepository.findById(idCardRequest.getTravelerId());
            Optional<Countries> countries = countriesRepository.findById(idCardRequest.getCountryCode());

            if(travelerList.isPresent())
            {
                if(countries.isPresent())
                {
                    IDCard idCard = idCardRequest.toIDCard(countries.get(), travelerList.get());

                    try {
                        idCardRepository.save(idCard);
                        return IDCardResponse.build(idCard);
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
    public List<IDCardResponse> searchTravelerListIdCard(UUID travelerId) {
        List<IDCard> allIdCard = idCardRepository.findAllIdCardByTravelerList(travelerId);
        List<IDCardResponse> allIdCardResponse = new ArrayList<>();
        for (IDCard idCard: allIdCard) {
            IDCardResponse idCardResponse = IDCardResponse.build(idCard);
            allIdCardResponse.add(idCardResponse);
        }
        return allIdCardResponse;
    }

    @Override
    public IDCardResponse searchIdCard(String idCardNumber) {
        Optional<IDCard> idCard = idCardRepository.findById(idCardNumber);
        return idCard.map(IDCardResponse::build).orElse(null);
    }

    @Override
    public IDCardResponse updateIdCard(IDCardRequest idCardRequest, String idCardNumber) {
        Optional<IDCard> isIdCard = idCardRepository.findById(idCardNumber);
        String message = null;
        if (isIdCard.isPresent()) {
            IDCard idCard = isIdCard.get();
            idCard.setIdCardNumber(idCardRequest.getIdCardNumber());
            idCard.setIdCardExpiry(idCardRequest.getIdCardExpiry());
            Optional<Countries> countries = countriesRepository.findById(idCardRequest.getCountryCode());
            if (countries.isPresent())
                idCard.setCountriesIDCard(countries.get());
            else
                message = "Countries with this code doesnt exist";

            if (message != null)
                return null;
            else {
                idCardRepository.saveAndFlush(idCard);
                return IDCardResponse.build(idCard);
            }
        }
        else
            return null;
    }
}
