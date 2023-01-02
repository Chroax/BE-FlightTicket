package com.binar.finalproject.BEFlightTicket.dummy;

import com.binar.finalproject.BEFlightTicket.model.Terminals;

import java.util.List;

public class TerminalDummy {

    public static List<Terminals> getTerminals() {
        Terminals terminals = new Terminals();
        Terminals terminals1 = new Terminals();
        terminals.setTerminalName("A1");
        terminals1.setTerminalName("B1");
        return List.of(terminals, terminals1);
    }
}
