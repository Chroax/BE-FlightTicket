package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.MessageModel;
import com.binar.finalproject.BEFlightTicket.service.InvoiceService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    HttpServletResponse response;

    @GetMapping("/generate/{orderId}")
    public void ResponseEntity(@PathVariable UUID orderId) throws Exception
    {
        MessageModel messageModel = new MessageModel();
        JasperPrint jasperPrint = invoiceService.generateInvoice(orderId);
        if(jasperPrint != null)
        {
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename=Invoice-MTicket-" + orderId + ".pdf");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Generated invoice");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            response.flushBuffer();
        }
    }
}
