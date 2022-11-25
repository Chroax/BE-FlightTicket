package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Terminals;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TerminalResponse {

    private Integer terminalId;
    private String terminalName;

    public static TerminalResponse build(Terminals terminals) {
        return TerminalResponse.builder()
                .terminalId(terminals.getTerminalId())
                .terminalName(terminals.getTerminalName())
                .build();
    }
}
