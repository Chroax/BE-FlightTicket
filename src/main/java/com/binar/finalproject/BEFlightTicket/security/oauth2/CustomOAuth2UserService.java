package com.binar.finalproject.BEFlightTicket.security.oauth2;

import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import com.binar.finalproject.BEFlightTicket.model.Users;
import com.binar.finalproject.BEFlightTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        return new CustomOAuth2User(user);
    }
    public UserResponse searchUserByNameOAuth(String fullName) {
        Users user = userRepository.findByNameOAuth(fullName);
        if(user != null)
            return UserResponse.build(user);
        else
            return null;
    }
}
