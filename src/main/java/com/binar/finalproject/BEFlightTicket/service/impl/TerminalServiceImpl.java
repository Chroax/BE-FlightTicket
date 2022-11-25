package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.TerminalRequest;
import com.binar.finalproject.BEFlightTicket.dto.TerminalResponse;
import com.binar.finalproject.BEFlightTicket.model.Terminals;
import com.binar.finalproject.BEFlightTicket.repository.TerminalsRepository;
import com.binar.finalproject.BEFlightTicket.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalsRepository terminalsRepository;

    @Override
    public TerminalResponse addTerminal(TerminalRequest terminalRequest) {
        Terminals terminals = terminalRequest.toTerminal();
        try {
            terminalsRepository.save(terminals);
            return TerminalResponse.build(terminals);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<TerminalResponse> searchAllTerminal() {
        List<Terminals> allTerminal = terminalsRepository.findAll();
        List<TerminalResponse> allTerminalResponses = new ArrayList<>();
        for (Terminals terminals : allTerminal) {
            TerminalResponse terminalResponse = TerminalResponse.build(terminals);
            allTerminalResponses.add(terminalResponse);
        }
        return allTerminalResponses;
    }

}
