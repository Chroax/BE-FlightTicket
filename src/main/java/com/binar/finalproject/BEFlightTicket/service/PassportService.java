package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;
import java.util.UUID;

public interface PassportService {
    PassportResponse registerPassport(PassportRequest passportRequest);
    List<PassportResponse> searchTravelerListPassport(UUID travelerId);
    PassportResponse searchPassport(String passportNumber);
    PassportResponse updatePassport(PassportRequest userUpdateRequest, String passportNumber);
}
