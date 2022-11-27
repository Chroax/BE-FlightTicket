package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createCity(@RequestBody CitiesRequest citiesRequest) {
        MessageModel messageModel = new MessageModel();
        CitiesResponse citiesResponse = citiesService.addCity(citiesRequest);
        if(citiesResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to create City");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Create new City");
            messageModel.setData(citiesResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }

    @PutMapping(value = "/update/{cityCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> updateCity(@PathVariable String cityCode, @RequestBody CitiesRequest citiesRequest)
    {
        MessageModel messageModel = new MessageModel();
        CitiesResponse citiesResponse = citiesService.updateCity(citiesRequest, cityCode);
        if(citiesResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update City");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update City with name : " + citiesResponse.getCityName());
            messageModel.setData(citiesResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageModel> getAllCity() {
        MessageModel messageModel = new MessageModel();
        try {
            List<CitiesResponse> getAllCity = citiesService.searchAllCity();
            messageModel.setMessage("Success get all City");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAllCity);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all City");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping(value = "/name/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getCountriesByName(@PathVariable String cityName){
        MessageModel messageModel = new MessageModel();
        try {
            CitiesResponse getCity = citiesService.searchCityByName(cityName);
            messageModel.setMessage("Success get City By name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getCity);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get City By Name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
