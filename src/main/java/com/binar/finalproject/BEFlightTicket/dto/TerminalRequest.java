package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Terminals;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TerminalRequest {

    @NotEmpty(message = "Terminal name is required.")
    private String terminalName;

    public Terminals toTerminal() {
        return Terminals.builder()
                .terminalName(this.terminalName)
                .build();
    }
}
