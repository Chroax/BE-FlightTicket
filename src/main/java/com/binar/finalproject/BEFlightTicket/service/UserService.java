package com.binar.finalproject.BEFlightTicket.service;

import com.binar.finalproject.BEFlightTicket.dto.UserRequest;
import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import com.binar.finalproject.BEFlightTicket.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    List<UserResponse> searchAllUser();
    UserResponse searchUserByName(String fullName);
    Boolean isEmailExist(String email);
    Boolean isPhoneNumberExist(String telephone);
    UserResponse updateUser(UserUpdateRequest userUpdateRequest, String fullName);
    Boolean deleteUser(String fullName);
}