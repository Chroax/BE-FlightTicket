package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.CountriesService;
import com.binar.finalproject.BEFlightTicket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {


    @Autowired
    private CountriesService countriesService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createCountries(@RequestBody CountriesRequest countriesRequest) {
        MessageModel messageModel = new MessageModel();
        CountriesResponse countriesResponse = countriesService.addCountries(countriesRequest);
        if(countriesResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to create countries");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Create new Countries");
            messageModel.setData(countriesResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageModel> getAllCountries() {
        MessageModel messageModel = new MessageModel();
        try {
            List<CountriesResponse> getAllCountries = countriesService.searchAllCountries();
            messageModel.setMessage("Success get all Countries");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAllCountries);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all countries");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

}
