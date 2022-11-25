package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.TerminalRequest;
import com.binar.finalproject.BEFlightTicket.dto.TerminalResponse;

import java.util.List;

public interface TerminalService {

    TerminalResponse addTerminal(TerminalRequest terminalRequest);
    List<TerminalResponse> searchAllTerminal();
    TerminalResponse updateTerminal(TerminalRequest terminalRequest, String terminalName);
    Boolean deleteTerminal(String terminalName);
    TerminalResponse searchTerminalByName(String terminalName);
}
