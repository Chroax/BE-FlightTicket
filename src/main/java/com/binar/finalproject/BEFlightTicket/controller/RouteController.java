package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.MessageModel;
import com.binar.finalproject.BEFlightTicket.dto.RouteRequest;
import com.binar.finalproject.BEFlightTicket.dto.RouteResponse;
import com.binar.finalproject.BEFlightTicket.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
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

    @GetMapping("/findby-city")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> getRouteByDepartureAndArrivalCity(@RequestParam String departureCity,@RequestParam String arrivalCity){
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

    @GetMapping("/findby-airport")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
    public ResponseEntity<MessageModel> getRouteByDepartureAndArrivalAirport(@RequestParam String departureAirport,@RequestParam String arrivalAirport){
        MessageModel messageModel = new MessageModel();
        try {
            List<RouteResponse> routeResponse = routeService.findByDepartureAndArrivalAirport(departureAirport, arrivalAirport);
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

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> getAllRoute()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<RouteResponse> routeGet = routeService.getAllRoute();
            messageModel.setMessage("Success get all route");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(routeGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all route");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> updateRoute(@RequestParam UUID routeId, @RequestBody RouteRequest routeRequest) {
        MessageModel messageModel = new MessageModel();
        RouteResponse routeResponse = routeService.updateRoute(routeRequest, routeId);

        if(routeResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update route with id : " + routeId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update route with id : " + routeId);
            messageModel.setData(routeResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageModel> deleteRoute(@RequestParam UUID routeId)
    {
        MessageModel messageModel = new MessageModel();
        Boolean deleteRoute = routeService.deleteRoute(routeId);
        if(deleteRoute)
        {
            messageModel.setMessage("Success delete route by id: " + routeId);
            messageModel.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(messageModel);
        }
        else
        {
            messageModel.setMessage("Failed delete route by id: " + routeId + ", not found");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }
}
