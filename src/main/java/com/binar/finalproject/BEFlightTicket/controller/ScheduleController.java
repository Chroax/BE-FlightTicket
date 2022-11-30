package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSchedule (@RequestBody ScheduleRequest scheduleRequest)
    {
        MessageModel messageModel = new MessageModel();
        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);
        if(scheduleResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to add schedule");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Add new schedule");
            messageModel.setData(scheduleResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @PutMapping("/update/{scheduleId}")
    public ResponseEntity<MessageModel> updateSchedule(@PathVariable UUID scheduleId, @RequestBody ScheduleRequest scheduleRequest) {
        MessageModel messageModel = new MessageModel();
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(scheduleRequest, scheduleId);

        if(scheduleResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update schedule with id : " + scheduleId);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Success update schedule with id : " + scheduleId);
            messageModel.setData(scheduleResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @GetMapping("/get-all/airplane/{airplaneName}")
    public ResponseEntity<MessageModel> getAirplaneSchedule(@PathVariable String airplaneName){
        MessageModel messageModel = new MessageModel();
        try {
            List<ScheduleResponse> scheduleResponses = scheduleService.searchAirplaneSchedule(airplaneName);
            messageModel.setMessage("Success get schedule by airplane name : " + airplaneName);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule by airplane name, " + airplaneName + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/get-all/route/{routeId}")
    public ResponseEntity<MessageModel> getRouteSchedule(@PathVariable UUID routeId){
        MessageModel messageModel = new MessageModel();
        try {
            List<ScheduleResponse> scheduleResponses = scheduleService.searchRouteSchedule(routeId);
            messageModel.setMessage("Success get schedule by route id : " + routeId);
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule by route id, " + routeId + " not found");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllSchedule()
    {
        MessageModel messageModel = new MessageModel();
        try {
            List<ScheduleResponse> scheduleGet = scheduleService.getAllSchedule();
            messageModel.setMessage("Success get all schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicket(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketSchedule(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-lower-price")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByLowerPrice(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByLowerPrice(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-higher-price")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByHigherPrice(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByHigherPrice(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-earliest-departure")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByEarliestDepartureTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByEarliestDepartureTime(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-latest-departure")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByLatestDepartureTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByLatestDepartureTime(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-earliest-arrival")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByEarliestArrivalTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate){
        MessageModel messageModel = new MessageModel();
        try {
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByEarliestArrivalTime(departureAirport, arrivalAirport, departureDate);
            messageModel.setMessage("Success get schedule");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
