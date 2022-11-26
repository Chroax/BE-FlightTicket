package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.GatesRepository;
import com.binar.finalproject.BEFlightTicket.service.GatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GatesServiceImpl implements GatesService {

    @Autowired
    private GatesRepository gatesRepository;

    @Override
    public GatesResponse addGates(GatesRequest gatesRequest) {
        try {
            Optional<Gates> terminals = gatesRepository.findById(gatesRequest.getTerminalId());
            if(terminals.isPresent())
            {
                Gates gates = Gates.builder()
                        .gateName(gatesRequest.getGateName())
                        .terminalsGates(terminals.get().getTerminalsGates())
                        .build();
                gatesRepository.saveAndFlush(gates);
                return GatesResponse.build(gates);
            }
            else
                return null;
        }
        catch (Exception ignore){
            return null;
        }
    }

    @Override
    public List<GatesResponse> searchAllGates() {
        List<Gates> allGates = gatesRepository.findAll();
        List<GatesResponse> allGatesResponse = new ArrayList<>();
        for (Gates  gates : allGates) {
            GatesResponse gatesResponse = GatesResponse.build(gates);
            allGatesResponse.add(gatesResponse);
        }
        return allGatesResponse;
    }

    @Override
    public GatesResponse updateGates(GatesRequest gatesRequest, String gateName) {
        return null;
    }

    @Override
    public Boolean deleteGates(String gateName) {
        return null;
    }

    @Override
    public GatesResponse searchGatesByName(String gateName) {
        return null;
    }
}
