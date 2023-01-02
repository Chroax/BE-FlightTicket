package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.config.EncoderConfiguration;
import com.binar.finalproject.BEFlightTicket.dto.UserRequest;
import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import com.binar.finalproject.BEFlightTicket.dto.UserUpdateRequest;
import com.binar.finalproject.BEFlightTicket.model.Countries;
import com.binar.finalproject.BEFlightTicket.model.Roles;
import com.binar.finalproject.BEFlightTicket.model.Users;
import com.binar.finalproject.BEFlightTicket.repository.RoleRepository;
import com.binar.finalproject.BEFlightTicket.repository.UserRepository;
import com.binar.finalproject.BEFlightTicket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        if(!isEmailExist(userRequest.getEmail()))
        {
            if(!isPhoneNumberExist(userRequest.getTelephone()))
            {
                try {
                    Roles roles = roleRepository.findByName("ROLE_BUYER");
                    if(roles != null)
                    {
                        Users users = userRequest.toUsers();
                        users.getRolesUsers().add(roles);
                        users.setPassword(encoder.encode(users.getPassword()));
                        userRepository.saveAndFlush(users);
                        return UserResponse.build(users);
                    }
                    else
                        return null;
                }
                catch (Exception ignore){
                    return null;
                }
            }
            else
                return null;
        }
        else
            return null;
    }

    @Override
    public List<UserResponse> searchAllUser() {
        List<Users> allUser = userRepository.findAll();
        return toListUserResponses(allUser);
    }

    @Override
    public UserResponse searchUserById(UUID userId) {
        Optional<Users> users = userRepository.findById(userId);
        if(users.isEmpty())
            return null;
        else
            return UserResponse.build(users.get());
    }

    @Override
    public Boolean isEmailExist(String email) {
        Optional<Users> users = userRepository.findByEmail(email);
        return users.isPresent();
    }

    @Override
    public Boolean isPhoneNumberExist(String telephone) {
        Users users = userRepository.findPhoneNumber(telephone);
        return users != null;
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest, UUID userId) {
        Users users = userRepository.findById(userId).get();
        String message = null;
        if (users != null) {
            users.setFullName(userUpdateRequest.getFullName());

            if(!isEmailExist(userUpdateRequest.getEmail()) || users.getEmail().equals(userUpdateRequest.getEmail()))
                users.setEmail(userUpdateRequest.getEmail());
            else
                message = "Email already exist";

            if(!isPhoneNumberExist(userUpdateRequest.getTelephone())  || users.getTelephone().equals(userUpdateRequest.getTelephone()))
                users.setTelephone(userUpdateRequest.getTelephone());
            else
                message = "Phone number already exist";

            users.setGender(userUpdateRequest.getGender());
            users.setBirthDate(userUpdateRequest.getBirthDate());
            users.setRolesUsers(users.getRolesUsers());

            if(message == null)
                return null;
            else
            {
                userRepository.saveAndFlush(users);
                return UserResponse.build(users);
            }
        } else
            return null;
    }

    @Override
    public Boolean deleteUser(String email) {
        Optional<Users> users = userRepository.findByEmail(email);
        if(users.isPresent())
        {
            users.get().setStatusActive(false);
            userRepository.saveAndFlush(users.get());
            return true;
        }
        else
            return false;
    }


    private List<UserResponse> toListUserResponses(List<Users> allUser) {
        List<UserResponse> allUserResponse = new ArrayList<>();
        for (Users user : allUser) {
            UserResponse userResponse = UserResponse.build(user);
            allUserResponse.add(userResponse);
        }
        return allUserResponse;
    }
}
