package com.binar.finalproject.BEFlightTicket.security.oauth2;

import com.binar.finalproject.BEFlightTicket.controller.OAuthController;
import com.binar.finalproject.BEFlightTicket.dto.GoogleLoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthorizedClientService implements OAuth2AuthorizedClientService {

    private final GoogleAccountService googleAccountService;

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication authentication) {
        GoogleLoginRequest googleLoginRequest = new GoogleLoginRequest();
        this.googleAccountService.oAuthLoginSuccess(googleLoginRequest);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
    }

}