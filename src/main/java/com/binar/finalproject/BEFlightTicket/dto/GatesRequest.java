package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Gates;
import com.binar.finalproject.BEFlightTicket.model.Terminals;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GatesRequest {

    @NotEmpty(message = "gate name is required.")
    private String gateName;

    @NotEmpty(message = "terminal is required.")
    private Integer terminalId;

    public Gates toGates(Terminals  terminals) {
        Gates gates = new Gates();
        gates.setGateName(this.gateName);
        gates.setTerminalsGates(terminals);
        return gates;
    }
}
