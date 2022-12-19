package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @PostMapping("/add-all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> addSchedule (@RequestBody List<ScheduleRequest> allScheduleRequest)
    {
        MessageModel messageModel = new MessageModel();
        List<ScheduleResponse> scheduleResponse = scheduleService.addAllSchedule(allScheduleRequest);
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
    @PreAuthorize("hasAnyRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUYER')")
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
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketSchedule(arrivalAirport, departureAirport, departureDate);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-lower-price/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByLowerPrice(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByLowerPrice(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-higher-price/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByHighestPrice(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByHigherPrice(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-earliest-departure/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByEarliestDepartureTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByEarliestDepartureTime(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-latest-departure/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByLatestDepartureTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByLatestDepartureTime(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-earliest-arrival/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByEarliestArrivalTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByEarliestArrivalTime(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/sort-latest-arrival/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicketOrderByLatestArrivalTime(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate,@PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<SearchScheduleResponse> scheduleResponses = scheduleService.searchAirplaneTicketOrderByLatestArrivalTime(arrivalAirport, departureAirport, departureDate, pageable);
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

    @GetMapping("/get-all/airport/{departureAirport}/{arrivalAirport}/date/{departureDate}/page/{page}/size{size}")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicket(@PathVariable String departureAirport,@PathVariable String arrivalAirport, @PathVariable String departureDate, @PathVariable Integer size, @PathVariable Integer page){
        MessageModel messageModel = new MessageModel();
        try {
            Pageable pageable = PageRequest.of(page, size);
            Iterable<SearchScheduleResponse> scheduleResponses = scheduleService.findByDepartureArrivalAirportAndDepartureDate(departureAirport, arrivalAirport, departureDate, pageable);
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
    
    @GetMapping("/id")
    public ResponseEntity<MessageModel> getAirplaneScheduleTicket(@RequestParam ("scheduleId") UUID scheduleId){
        MessageModel messageModel = new MessageModel();
        try {
            SearchScheduleResponse scheduleResponses = scheduleService.searchScheduleDetails(scheduleId);
            messageModel.setMessage("Success get schedule details");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get schedule details");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

}
