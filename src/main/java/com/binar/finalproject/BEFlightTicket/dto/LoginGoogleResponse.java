package com.binar.finalproject.BEFlightTicket.dto;

import com.binar.finalproject.BEFlightTicket.model.Users;
import com.binar.finalproject.BEFlightTicket.security.oauth2.CustomOAuth2User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
@Builder
public class LoginGoogleResponse {

        private String token;
        private String type;
        private String email;
        private String fullName;

        public static LoginGoogleResponse build(String jwt, CustomOAuth2User oAuth2User) {
            return LoginGoogleResponse.builder()
                    .token(jwt)
                    .type("Bearer")
                    .email(oAuth2User.getEmail())
                    .fullName(oAuth2User.getName())
                    .build();
        }
    }
