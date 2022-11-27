package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.model.Routes;
import com.binar.finalproject.BEFlightTicket.repository.RouteRepository;
import com.binar.finalproject.BEFlightTicket.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public RouteResponse addRoute(RouteRequest routeRequest) {
        Routes routes = routeRequest.toRoutes();

        try {
            routeRepository.save(routes);
            return RouteResponse.build(routes);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<RouteResponse> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity) {
        List<Routes> allRoute = routeRepository.findRouteByDepartureCityAndArrivalCity(departureCity, arrivalCity);
        List<RouteResponse> allRouteResponse = new ArrayList<>();
        for (Routes routes : allRoute)
        {
            RouteResponse routeResponse = RouteResponse.build(routes);
            allRouteResponse.add(routeResponse);
        }
        return allRouteResponse;
    }

    @Override
    public List<RouteResponse> findByDepartureAndArrivalAirport(String departureAirport, String arrivalAirport) {
        List<Routes> allRoute = routeRepository.findRouteByDepartureAndArrivalAirport(departureAirport,arrivalAirport);
        List<RouteResponse> allRouteResponse = new ArrayList<>();
        for (Routes routes : allRoute)
        {
            RouteResponse routeResponse = RouteResponse.build(routes);
            allRouteResponse.add(routeResponse);
        }
        return allRouteResponse;
    }


    @Override
    public List<RouteResponse> getAllRoute() {
        List<Routes> allRoute = routeRepository.findAll();
        List<RouteResponse> allRouteResponse = new ArrayList<>();
        for (Routes routes : allRoute)
        {
            RouteResponse routeResponse = RouteResponse.build(routes);
            allRouteResponse.add(routeResponse);
        }
        return allRouteResponse;
    }

    @Override
    public RouteResponse updateRoute(RouteRequest routeRequest, UUID routeId) {
        Optional<Routes> isRoutes = routeRepository.findById(routeId);
        if (isRoutes.isPresent())
        {
            Routes routes = isRoutes.get();
            routes.setDepartureCity(routeRequest.getDepartureCity());
            routes.setArrivalCity(routeRequest.getArrivalCity());
            routes.setDepartureAirport(routeRequest.getDepartureAirport());
            routes.setArrivalAirport(routeRequest.getArrivalAirport());
            routes.setDepartureTerminal(routeRequest.getDepartureTerminal());
            routes.setArrivalTerminal(routeRequest.getArrivalTerminal());
            try {
                routeRepository.save(routes);
                return RouteResponse.build(routes);
            }
            catch(Exception exception)
            {
                return null;
            }
        }
        else
            throw new RuntimeException("Route with id: " + routeId + " not found");
    }

    @Override
    public Boolean deleteRoute(UUID routeId) {
        Optional<Routes> isRoute = routeRepository.findById(routeId);
        if (isRoute.isPresent()){
            Routes routes = isRoute.get();
            routeRepository.deleteById(routes.getRouteId());
            return true;
        }
        return null;
    }
}
