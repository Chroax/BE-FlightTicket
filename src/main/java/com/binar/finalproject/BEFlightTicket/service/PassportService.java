package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.UUID;

public interface PassportService {
    PassportResponse registerPassport(PassportRequest passportRequest);
    PassportResponse searchTravelerListPassport(UUID travelerId);
    PassportResponse searchPassport(UUID passportId);
    PassportResponse updatePassport(PassportRequest userUpdateRequest, UUID passportId);
}
