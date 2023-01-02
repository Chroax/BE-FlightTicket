package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;
import java.util.UUID;

public interface TravelerListService {
    TravelerListResponse registerTravelerList(TravelerListRequest travelerListRequest);
    List<TravelerListResponse> searchAllTravelerList();
    List<TravelerListDetailResponse> autoComplete(UUID userId);
    List<TravelerListResponse> searchAllUserTravelerList(UUID userId);
    TravelerListResponse updateTravelerList(TravelerListUpdateRequest userUpdateRequest, UUID travelerId);
    List<TravelerListDetailResponse> registerTravelerListFromOrder(List<TravelerListDetailRequest> travelerListDetailRequest);
}
