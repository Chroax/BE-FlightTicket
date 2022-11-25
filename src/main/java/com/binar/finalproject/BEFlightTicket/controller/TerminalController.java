package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terminals")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createCountries(@RequestBody TerminalRequest terminalRequest) {
        MessageModel messageModel = new MessageModel();
        TerminalResponse terminalResponse = terminalService.addTerminal(terminalRequest);
        if(terminalResponse == null) {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to create terminals");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Create new terminals");
            messageModel.setData(terminalResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getAllRole() {
        MessageModel messageModel = new MessageModel();
        try {
            List<TerminalResponse> getAllTerminal = terminalService.searchAllTerminal();
            messageModel.setMessage("Success get all Terminal");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getAllTerminal);
            return ResponseEntity.ok().body(messageModel);
        } catch (Exception exception) {
            messageModel.setMessage("Failed get all Terminal");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @PutMapping(value = "/update/{terminalName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> updateRole(@PathVariable String terminalName, @RequestBody TerminalRequest terminalRequest)
    {
        MessageModel messageModel = new MessageModel();
        TerminalResponse terminalResponse = terminalService.updateTerminal(terminalRequest, terminalName);

        if(terminalResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed to update Terminal");
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update Terminal with name : " + terminalResponse.getTerminalName());
            messageModel.setData(terminalResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @DeleteMapping(value = "/delete/{terminalName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> deleteCityByName(@PathVariable String terminalName)
    {
        MessageModel messageModel = new MessageModel();
        Boolean deleteTerminal = terminalService.deleteTerminal(terminalName);
        if(deleteTerminal)
        {
            messageModel.setMessage("Success delete Terminal by name : " + terminalName);
            messageModel.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(messageModel);
        }
        else
        {
            messageModel.setMessage("Failed delete Terminal by name : " + terminalName + ", is not found");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }

    @GetMapping(value = "/get-byName/{terminalName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageModel> getAirportsByName(@PathVariable String terminalName){
        MessageModel messageModel = new MessageModel();
        try {
            TerminalResponse getTerminal = terminalService.searchTerminalByName(terminalName);
            messageModel.setMessage("Success get Terminal By name");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(getTerminal);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get Terminal By Name");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }
}
