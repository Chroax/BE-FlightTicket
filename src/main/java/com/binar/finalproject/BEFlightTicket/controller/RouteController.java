package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.MessageModel;
import com.binar.finalproject.BEFlightTicket.dto.RouteRequest;
import com.binar.finalproject.BEFlightTicket.dto.RouteResponse;
import com.binar.finalproject.BEFlightTicket.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addRoute(@RequestBody RouteRequest routeRequest)
    {
        MessageModel messageModel = new MessageModel();
        RouteResponse routeResponse = routeService.addRoute(routeRequest);
        if(routeResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add route");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Add new route");
            messageModel.setData(routeResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
    @GetMapping("/get-all/{departureCity}/{arrivalCity}")
    public ResponseEntity<MessageModel> getRouteByDepartureAndArrivalCity(@PathVariable String departureCity,@PathVariable String arrivalCity){
        MessageModel messageModel = new MessageModel();
        try {
            List<RouteResponse> routeResponse = routeService.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
            messageModel.setMessage("Success get route");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(routeResponse);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get route");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
