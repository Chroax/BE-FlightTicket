package com.binar.finalproject.BEFlightTicket.controller;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class OAuth2Controller {

    @GetMapping("/login")
    public Info getInfo(OAuth2AuthenticationToken authentication){
        return new Info()
                .setApplication("google-login")
                .setPrincipal(authentication.getPrincipal().getAttributes());
    }

    @Data
    @Accessors(chain = true)
    private class Info {
        private String application;
        private Map<String, Object> principal;
    }
}
