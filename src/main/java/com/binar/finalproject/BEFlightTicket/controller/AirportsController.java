package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportsController {
    @Autowired
    private AirportService airportService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createAirports(@RequestBody AirportRequest airportRequest) {
        MessageModel messageModel = new MessageModel();
        AirportResponse airportResponse = airportService.addAirports(airportRequest);
        if(airportResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to create Airports");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Create new Airports");
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getAllAirports()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<AirportResponse> getAirport = airportService.searchAllAirports();
            messageModel.setMessage("Success get all Airports");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAirport);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all Airports");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PutMapping(value = "/update/{iataCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> updateAirports(@PathVariable String iataCode, @RequestBody AirportRequest airportRequest)
    {
        MessageModel messageModel = new MessageModel();
        AirportResponse airportResponse = airportService.updateAirports(airportRequest, iataCode);
        if(airportResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update Airports");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update Airport with name : " + airportResponse.getAirportName());
            messageModel.setData(airportResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping(value = "/name/{airportName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getAirportsByName(@PathVariable String airportName){
        MessageModel messageModel = new MessageModel();
        try {
            AirportResponse getAirport = airportService.searchAirportsByName(airportName);
            messageModel.setMessage("Success get Airport By name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAirport);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get Airport By Name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping(value = "/city/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getAirportsByCity(@PathVariable String cityName){
        MessageModel messageModel = new MessageModel();
        try {
            List<AirportSearchResponse> getAirport = airportService.searchAirportByCityName(cityName);
            messageModel.setMessage("Success get Airport By City");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAirport);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get Airport By City");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
