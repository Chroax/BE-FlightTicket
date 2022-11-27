package com.binar.finalproject.BEFlightTicket.service;


import com.binar.finalproject.BEFlightTicket.dto.IDCardRequest;
import com.binar.finalproject.BEFlightTicket.dto.IDCardResponse;

import java.util.List;
import java.util.UUID;

public interface IdCardService {

    IDCardResponse registerIdCard(IDCardRequest idCardRequest);
    List<IDCardResponse> searchTravelerListIdCard(UUID travelerId);
    IDCardResponse searchIdCard(String idCardNumber);
    IDCardResponse updateIdCard(IDCardRequest idCardRequest, String idCardNumber);
}
