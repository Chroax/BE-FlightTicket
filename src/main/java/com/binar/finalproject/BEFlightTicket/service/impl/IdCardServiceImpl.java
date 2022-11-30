package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.IdCardRequest;
import com.binar.finalproject.BEFlightTicket.dto.IdCardResponse;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.IdCard;
import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import com.binar.finalproject.BEFlightTicket.repository.CountriesRepository;
import com.binar.finalproject.BEFlightTicket.repository.IdCardRepository;
import com.binar.finalproject.BEFlightTicket.repository.TravelerListRepository;
import com.binar.finalproject.BEFlightTicket.service.IdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public IdCardResponse registerIdCard(IdCardRequest idCardRequest) {
        try{
            Optional<TravelerList> travelerList = travelerListRepository.findById(idCardRequest.getTravelerId());
            Optional<Countries> countries = countriesRepository.findById(idCardRequest.getCountryCode());

            if(travelerList.isPresent())
            {
                if(countries.isPresent())
                {
                    IdCard idCard = idCardRequest.toIdCard(countries.get(), travelerList.get());

                    try {
                        idCardRepository.save(idCard);
                        return IdCardResponse.build(idCard);
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
    public IdCardResponse searchTravelerListIdCard(UUID travelerId) {
        IdCard idCard = idCardRepository.findAllIdCardByTravelerList(travelerId);
        return IdCardResponse.build(idCard);
    }

    @Override
    public IdCardResponse searchIdCard(UUID idCardId) {
        Optional<IdCard> idCard = idCardRepository.findById(idCardId);
        return idCard.map(IdCardResponse::build).orElse(null);
    }

    @Override
    public IdCardResponse updateIdCard(IdCardRequest idCardRequest, UUID idCardId) {
        Optional<IdCard> isIdCard = idCardRepository.findById(idCardId);
        String message = null;
        if (isIdCard.isPresent()) {
            IdCard idCard = isIdCard.get();
            idCard.setIdCardNumber(idCardRequest.getIdCardNumber());
            idCard.setIdCardExpiry(idCardRequest.getIdCardExpiry());
            Optional<Countries> countries = countriesRepository.findById(idCardRequest.getCountryCode());
            if (countries.isPresent())
                idCard.setCountriesIdCard(countries.get());
            else
                message = "Countries with this code doesnt exist";

            if (message != null)
                return null;
            else {
                idCardRepository.saveAndFlush(idCard);
                return IdCardResponse.build(idCard);
            }
        }
        else
            return null;
    }
}
