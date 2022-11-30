package com.binar.finalproject.BEFlightTicket.service;


import com.binar.finalproject.BEFlightTicket.dto.IdCardRequest;
import com.binar.finalproject.BEFlightTicket.dto.IdCardResponse;

import java.util.UUID;

public interface IdCardService {
    IdCardResponse registerIdCard(IdCardRequest idCardRequest);
    IdCardResponse searchTravelerListIdCard(UUID travelerId);
    IdCardResponse searchIdCard(UUID idCardId);
    IdCardResponse updateIdCard(IdCardRequest idCardRequest, UUID idCardId);
}
