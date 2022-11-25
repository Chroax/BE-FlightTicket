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

    @PostMapping(value = "/add-city", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PutMapping(value = "/update/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> updateCity(@PathVariable String cityName, @RequestBody CitiesRequest citiesRequest)
    {
        MessageModel messageModel = new MessageModel();
        CitiesResponse citiesResponse = citiesService.updateCity(citiesRequest, cityName);
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
}
