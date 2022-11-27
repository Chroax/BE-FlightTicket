package com.binar.finalproject.BEFlightTicket.service;


import com.binar.finalproject.BEFlightTicket.dto.IdCardRequest;
import com.binar.finalproject.BEFlightTicket.dto.IdCardResponse;

import java.util.List;
import java.util.UUID;

public interface IdCardService {

    IdCardResponse registerIdCard(IdCardRequest idCardRequest);
    List<IdCardResponse> searchTravelerListIdCard(UUID travelerId);
    IdCardResponse searchIdCard(String idCardNumber);
    IdCardResponse updateIdCard(IdCardRequest idCardRequest, String idCardNumber);
}
