package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.MessageModel;
import com.binar.finalproject.BEFlightTicket.dto.RoleRequest;
import com.binar.finalproject.BEFlightTicket.dto.RoleResponse;
import com.binar.finalproject.BEFlightTicket.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> createRole(@RequestBody RoleRequest roleRequest)
    {
        MessageModel messageModel = new MessageModel();
        RoleResponse roleResponse = roleService.registerRole(roleRequest);
        if(roleResponse == null)
        {
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            messageModel.setMessage("Failed to create roles");
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.CREATED.value());
            messageModel.setMessage("Create new roles");
            messageModel.setData(roleResponse);
            return ResponseEntity.ok().body(messageModel);

        }
    }

}
