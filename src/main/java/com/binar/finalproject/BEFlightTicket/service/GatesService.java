package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.*;

import java.util.List;

public interface GatesService {

    GatesResponse addGates(GatesRequest  gatesRequest);
    List<GatesResponse> searchAllGates();
    GatesResponse updateGates(GatesRequest gatesRequest, String gateName);
    GatesResponse searchGatesByName(String gateName);
}
