package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.GatesRepository;
import com.binar.finalproject.BEFlightTicket.repository.TerminalsRepository;
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
    @Autowired
    private TerminalsRepository terminalsRepository;

    @Override
    public GatesResponse addGates(GatesRequest gatesRequest) {
        try {
            Optional<Terminals> terminals = terminalsRepository.findById(gatesRequest.getTerminalId());
            if(terminals.isPresent())
            {
                Gates gates = Gates.builder()
                        .gateName(gatesRequest.getGateName())
                        .terminalsGates(terminals.get())
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
    public GatesResponse updateGates(GatesRequest gatesRequest, Integer gateId) {
        Optional<Gates> isGates = gatesRepository.findById(gateId);
        String message = null;
        if (isGates.isPresent()) {
            Gates gates = isGates.get();

            gates.setGateName(gatesRequest.getGateName());

            Optional<Terminals> terminals = terminalsRepository.findById(gatesRequest.getTerminalId());
            if(terminals.isPresent())
                gates.setTerminalsGates(terminals.get());
            else
                message = "Terminals with this id doesnt exist";

            if(message != null)
                return null;
            else
            {
                gatesRepository.saveAndFlush(gates);
                return GatesResponse.build(gates);
            }
        } else
            return null;
    }
}
