package com.binar.finalproject.BEFlightTicket.controller;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.security.JwtUtils;
import com.binar.finalproject.BEFlightTicket.service.UserService;
import com.binar.finalproject.BEFlightTicket.service.impl.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerUser(@RequestBody UserRequest userRequest) {
        MessageModel messageModel = new MessageModel();

        UserResponse userResponse = userService.registerUser(userRequest);
        if(userResponse == null)
        {
            messageModel.setMessage("Failed register new user");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Register new user");
            messageModel.setData(userResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageModel> registerUser(@RequestBody LoginRequest loginRequest) {
        MessageModel messageModel = new MessageModel();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        messageModel.setData(LoginResponse.build(jwt, userDetails));
        messageModel.setStatus(HttpStatus.OK.value());
        messageModel.setMessage("Success Login");

        return ResponseEntity.ok().body(messageModel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<MessageModel> getAllUsers(){
        MessageModel messageModel = new MessageModel();
        try {
            List<UserResponse> usersGet = userService.searchAllUser();
            messageModel.setMessage("Success get all user");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(usersGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get all user");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @GetMapping("/name/{fullName}")
    public ResponseEntity<MessageModel> getUserById(@PathVariable String fullName){
        MessageModel messageModel = new MessageModel();
        try {
            UserResponse userGet = userService.searchUserByName(fullName);
            messageModel.setMessage("Success get user");
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setData(userGet);
            return ResponseEntity.ok().body(messageModel);
        }catch (Exception exception)
        {
            messageModel.setMessage("Failed get user");
            messageModel.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageModel);
        }
    }

    @DeleteMapping("/delete/{fullName}")
    public ResponseEntity<MessageModel> deleteUser(@PathVariable String fullName){
        MessageModel messageModel = new MessageModel();
        Boolean deleteUser = userService.deleteUser(fullName);
        if(deleteUser)
        {
            messageModel.setMessage("Success non-active user by name : " + fullName);
            messageModel.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok().body(messageModel);
        }
        else
        {
            messageModel.setMessage("Failed non-active user by name : " + fullName + ", not found");
            messageModel.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(messageModel);
        }
    }

    @PutMapping("/update/{fullName}")
    public ResponseEntity<MessageModel> updateUser(@PathVariable String fullName, @RequestBody UserUpdateRequest userUpdateRequest) {
        MessageModel messageModel = new MessageModel();
        UserResponse userResponse = userService.updateUser(userUpdateRequest, fullName);

        if(userResponse == null)
        {
            messageModel.setStatus(HttpStatus.CONFLICT.value());
            messageModel.setMessage("Failed update user with name : " + fullName);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(messageModel);
        }
        else
        {
            messageModel.setStatus(HttpStatus.OK.value());
            messageModel.setMessage("Update user with name : " + fullName);
            messageModel.setData(userResponse);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
