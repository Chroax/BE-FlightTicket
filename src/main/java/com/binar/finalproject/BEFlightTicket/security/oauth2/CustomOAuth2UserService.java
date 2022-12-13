package com.binar.finalproject.BEFlightTicket.security.oauth2;

import com.binar.finalproject.BEFlightTicket.model.AuthenticationProvider;
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

    public void oAuthLoginSuccess(Users users, String email, String fullName, String googleId) {
        Users userGoogle = userRepository.findByGoogleId(googleId);
        if (userGoogle == null) {
            Users userWithGmail = userRepository.findByGmail(email);
            if (userWithGmail == null) {
                users.setEmail(email);
                users.setFullName(fullName);
                users.setStatusActive(true);
                users.setAuthProvider(AuthenticationProvider.GOOGLE);
                users.setGoogleId(googleId);
                oAuth2Password(users);
                userRepository.save(users);
            }
        }
    }

    public void oAuth2Password(Users users){
        String googleId = users.getGoogleId();
        String clientId = "953090499155-f5pgpt16s6lhge53hhi4s5cm5dg18in3.apps.googleusercontent.com";
        String googlePassword = googleId + clientId;
        users.setPassword(String.valueOf(googlePassword.hashCode()));
    }
}
