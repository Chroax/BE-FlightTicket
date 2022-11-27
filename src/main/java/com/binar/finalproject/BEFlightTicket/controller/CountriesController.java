package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.CountriesService;
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

    @PutMapping(value = "/update/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> updateCountries(@PathVariable String countryName, @RequestBody CountriesRequest countriesRequest)
    {
        MessageModel messageModel = new MessageModel();
        CountriesResponse countriesResponse = countriesService.updateCountries(countriesRequest, countryName);
        if(countriesResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update Countries");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update Countries with name : " + countriesResponse.getCountryName());
            messageModel.setData(countriesResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @DeleteMapping(value = "/delete/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> deleteCountriesByName(@PathVariable String countryName)
    {
        MessageModel messageModel = new MessageModel();
        Boolean deleteCountries = countriesService.deleteCountries(countryName);
        if(deleteCountries)
        {
            messageModel.setMessage("Success delete Countries by name : " + countryName);
            messageModel.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(messageModel);
        }
        else
        {
            messageModel.setMessage("Failed delete Countries by name : " + countryName + ", is not found");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }

    @GetMapping(value = "/name/{countryName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getCountriesByName(@PathVariable String countryName){
        MessageModel messageModel = new MessageModel();
        try {
            CountriesResponse getCountries = countriesService.searchCountriesByName(countryName);
            messageModel.setMessage("Success get Countries By name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getCountries);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get Countries By Name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

}
