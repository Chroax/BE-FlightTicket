package com.binar.finalproject.BEFlightTicket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("FlightTicket")
                        .description("API for Application Flight Ticketing")
                        .version("1.0.0"))
                .servers(servers());
    }

    private List<Server> servers() {
        List<Server> servers = new ArrayList<>();

        Server serverDev = new Server();
        serverDev.setUrl("http://localhost:8080/");
        serverDev.setDescription("Main server for Dev");

        servers.add(serverDev);
        return servers;
    }
}
