package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public ByteArrayInputStream generateInvoice(UUID orderId) {
        return null;
    }
}
