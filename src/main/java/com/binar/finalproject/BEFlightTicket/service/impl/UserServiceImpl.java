package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.config.EncoderConfiguration;
import com.binar.finalproject.BEFlightTicket.dto.UserRequest;
import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import com.binar.finalproject.BEFlightTicket.dto.UserUpdateRequest;
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
                    Optional<Roles> roles = roleRepository.findById(userRequest.getRolesId());
                    if(roles.isPresent())
                    {
                        Users users = userRequest.toUsers();
                        users.getRolesUsers().add(roles.get());
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
    public UserResponse searchUserByName(String fullName) {
        Users users = userRepository.findByName(fullName);
        if(users != null)
            return UserResponse.build(users);
        else
            return null;
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
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest, String fullName) {
        Users users = userRepository.findByName(fullName);
        String message = null;
        if (users != null) {
            users.setFullName(userUpdateRequest.getFullName());

            if(!isEmailExist(userUpdateRequest.getEmail()))
                users.setEmail(userUpdateRequest.getEmail());
            else
                message = "Email already exist";

            if(!isPhoneNumberExist(userUpdateRequest.getTelephone()))
                users.setTelephone(userUpdateRequest.getTelephone());
            else
                message = "Phone number already exist";

            users.setGender(userUpdateRequest.getGender());
            users.setBirthDate(userUpdateRequest.getBirthDate());

            Optional<Roles> roles = roleRepository.findById(userUpdateRequest.getRolesId());
            if(roles.isPresent())
                users.getRolesUsers().add(roles.get());
            else
                message = "Role with this id doesnt exist";

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
    public Boolean deleteUser(String fullName) {
        Users users = userRepository.findByName(fullName);
        if(users != null)
        {
            users.setStatusActive(false);
            userRepository.saveAndFlush(users);
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
