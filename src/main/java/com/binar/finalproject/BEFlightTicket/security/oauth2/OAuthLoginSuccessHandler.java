package com.binar.finalproject.BEFlightTicket.security.oauth2;

import com.binar.finalproject.BEFlightTicket.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class OAuthLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException,  IOException{
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String fullName = oAuth2User.getName();
        UserResponse user = customOAuth2UserService.searchUserByNameOAuth(fullName);
        System.out.println("Customer's email: "+email);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
