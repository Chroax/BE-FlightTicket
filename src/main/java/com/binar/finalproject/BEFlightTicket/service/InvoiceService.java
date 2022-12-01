package com.binar.finalproject.BEFlightTicket.service;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public interface InvoiceService {

    ByteArrayInputStream generateInvoice(UUID orderId);
}
