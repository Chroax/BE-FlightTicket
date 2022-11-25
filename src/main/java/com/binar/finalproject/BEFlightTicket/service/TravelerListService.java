package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;
import java.util.UUID;

public interface TravelerListService {

    TravelerListResponse registerTravelerList(TravelerListRequest userRequest);
    List<TravelerListResponse> searchAllTravelerList();
    List<TravelerListResponse> searchAllUserTravelerList(UUID userId);
    TravelerListResponse updateTravelerList(TravelerListUpdateRequest userUpdateRequest, UUID travelerId);
    Boolean deleteTravelerList(UUID travelerId);
}
