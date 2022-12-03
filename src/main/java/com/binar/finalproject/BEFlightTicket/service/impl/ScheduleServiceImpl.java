package com.binar.finalproject.BEFlightTicket.service.impl;

import com.binar.finalproject.BEFlightTicket.dto.*;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.AirplanesRepository;
import com.binar.finalproject.BEFlightTicket.repository.RouteRepository;
import com.binar.finalproject.BEFlightTicket.repository.ScheduleRepository;
import com.binar.finalproject.BEFlightTicket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AirplanesRepository airplanesRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        try{
            Optional<Airplanes> airplanes = airplanesRepository.findById(scheduleRequest.getAirplaneName());
            Optional<Routes> routes = routeRepository.findById(scheduleRequest.getRouteId());

            if(airplanes.isPresent())
            {
                if(routes.isPresent())
                {
                    Schedules schedules = scheduleRequest.toSchedule(airplanes.get(), routes.get());

                    try {
                        scheduleRepository.save(schedules);
                        return ScheduleResponse.build(schedules);
                    }
                    catch(Exception exception)
                    {
                        return null;
                    }
                }
                else
                    return null;
            }
            else
                return null;
        }catch (Exception exception)
        {
            return null;
        }
    }

    @Override
    public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest, UUID scheduleId) {
        Optional<Schedules> isSchedule = scheduleRepository.findById(scheduleId);
        String message = null;
        if (isSchedule.isPresent())
        {
            Schedules schedules = isSchedule.get();
            schedules.setDepartureDate(scheduleRequest.getDepartureDate());
            schedules.setArrivalDate(scheduleRequest.getArrivalDate());
            schedules.setDepartureTime(scheduleRequest.getDepartureTime());
            schedules.setArrivalTime(scheduleRequest.getArrivalTime());
            schedules.setPrice(scheduleRequest.getPrice());
            schedules.setStatus(scheduleRequest.getStatus());

            Optional<Airplanes> airplanes = airplanesRepository.findById(scheduleRequest.getAirplaneName());
            if (airplanes.isPresent())
                schedules.setAirplanesSchedules(airplanes.get());
            else
                message = "Airplane with this name doesnt exist";

            Optional<Routes> routes = routeRepository.findById(scheduleRequest.getRouteId());
            if (routes.isPresent())
                schedules.setRoutesSchedules(routes.get());
            else
                message = "Route with this id doesnt exist";

            if (message != null)
                return null;
            else
            {
                scheduleRepository.saveAndFlush(schedules);
                return ScheduleResponse.build(schedules);
            }
        }
        else
            return null;
    }

    @Override
    public List<ScheduleResponse> searchAirplaneSchedule(String airplaneName) {
        List<Schedules> allSchedule = scheduleRepository.getAllAirplaneSchedule(airplaneName);
        return toListScheduleResponse(allSchedule);
    }

    @Override
    public List<ScheduleResponse> searchRouteSchedule(UUID routeId) {
        List<Schedules> allSchedule = scheduleRepository.getAllRouteSchedule(routeId);
        return toListScheduleResponse(allSchedule);
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketSchedule(String arrivalAirport, String departureAirport, String departureDate) {
        List<Schedules> allSchedule = scheduleRepository.searchScheduleTicket(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Routes> allRoute = routeRepository.searchRouteTicket(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Airplanes> allAirplane = airplanesRepository.searchAirplaneTicket(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<SearchScheduleResponse> allSearchScheduleResponse = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            for (Routes routes : allRoute)
            {
                for (Airplanes airplanes : allAirplane)
                {
                    SearchScheduleResponse searchScheduleResponse = SearchScheduleResponse.build(schedules, routes, airplanes);
                    allSearchScheduleResponse.add(searchScheduleResponse);
                    break;
                }
                break;
            }

        }
        return allSearchScheduleResponse;
    }

    @Override
    public List<ScheduleResponse> getAllSchedule() {
        List<Schedules> allSchedule = scheduleRepository.findAll();
        return toListScheduleResponse(allSchedule);
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByLowerPrice(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable) {
        Iterable<Schedules> allSchedule = scheduleRepository.searchScheduleTicketOrderByLowerPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Iterable<Routes> allRoute = routeRepository.searchRouteTicketOrderByLowerPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Iterable<Airplanes> allAirplane = airplanesRepository.searchAirplaneTicketOrderByLowerPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        return toListSearchScheduleResponse(allSchedule, allRoute, allAirplane);
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByHigherPrice(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable) {
        Page<Schedules> allSchedule = scheduleRepository.searchScheduleTicketOrderByHigherPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Page<Routes> allRoute = routeRepository.searchRouteTicketOrderByHigherPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Page<Airplanes> allAirplane = airplanesRepository.searchAirplaneTicketOrderByHigherPrice(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        return toListSearchScheduleResponse(allSchedule, allRoute, allAirplane);
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByEarliestDepartureTime(String arrivalAirport, String departureAirport, String departureDate, Pageable pageable) {
        Page<Schedules> allSchedule = scheduleRepository.searchScheduleTicketByEarliestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Page<Routes> allRoute = routeRepository.searchRouteTicketByEarliestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        Page<Airplanes> allAirplane = airplanesRepository.searchScheduleTicketByEarliestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate), pageable);
        return toListSearchScheduleResponse(allSchedule, allRoute, allAirplane);
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByLatestDepartureTime(String arrivalAirport, String departureAirport, String departureDate) {
        List<Schedules> allSchedule = scheduleRepository.searchScheduleTicketByLatestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Routes> allRoute = routeRepository.searchRouteTicketByLatestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Airplanes> allAirplane = airplanesRepository.searchAirplaneTicketByLatestDepartureTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<SearchScheduleResponse> orderByLatestDepartureTime = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            for (Routes routes : allRoute)
            {
                for (Airplanes airplanes : allAirplane)
                {
                    SearchScheduleResponse searchScheduleResponse = SearchScheduleResponse.build(schedules, routes, airplanes);
                    orderByLatestDepartureTime.add(searchScheduleResponse);
                    break;
                }
                break;
            }

        }
        return orderByLatestDepartureTime;
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByEarliestArrivalTime(String arrivalAirport, String departureAirport, String departureDate) {
        List<Schedules> allSchedule = scheduleRepository.searchScheduleTicketByEarliestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Routes> allRoute = routeRepository.searchScheduleTicketByEarliestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Airplanes> allAirplane = airplanesRepository.searchScheduleTicketByEarliestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<SearchScheduleResponse> orderByEarliestArrivalTime = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            for (Routes routes : allRoute)
            {
                for (Airplanes airplanes : allAirplane)
                {
                    SearchScheduleResponse searchScheduleResponse = SearchScheduleResponse.build(schedules, routes, airplanes);
                    orderByEarliestArrivalTime.add(searchScheduleResponse);
                    break;
                }
                break;
            }

        }
        return orderByEarliestArrivalTime;
    }

    @Override
    public List<SearchScheduleResponse> searchAirplaneTicketOrderByLatestArrivalTime(String arrivalAirport, String departureAirport, String departureDate) {
        List<Schedules> allSchedule = scheduleRepository.searchScheduleTicketByLatestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Routes> allRoute = routeRepository.searchScheduleTicketByLatestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<Airplanes> allAirplane = airplanesRepository.searchScheduleTicketByLatestArrivalTime(arrivalAirport, departureAirport, LocalDate.parse(departureDate));
        List<SearchScheduleResponse> orderByLatestArrivalTime = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            for (Routes routes : allRoute)
            {
                for (Airplanes airplanes : allAirplane)
                {
                    SearchScheduleResponse searchScheduleResponse = SearchScheduleResponse.build(schedules, routes, airplanes);
                    orderByLatestArrivalTime.add(searchScheduleResponse);
                    break;
                }
                break;
            }

        }
        return orderByLatestArrivalTime;
    }
    @Override
    public Iterable<SearchScheduleResponse> findByDepartureArrivalAirportAndDepartureDate(String departureAirport, String arrivalAirport, String departureDate, Pageable pageable) {
        Iterable<Schedules> allSchedule = scheduleRepository.searchTicket(departureAirport, arrivalAirport, LocalDate.parse(departureDate), pageable);
        Iterable<Routes> allRoute = routeRepository.searchTicket(departureAirport, arrivalAirport, LocalDate.parse(departureDate), pageable);
        Iterable<Airplanes> allAirplane = airplanesRepository.searchTicket(departureAirport, arrivalAirport, LocalDate.parse(departureDate), pageable);
        return toListSearchScheduleResponse(allSchedule, allRoute, allAirplane);
    }

    private List<ScheduleResponse> toListScheduleResponse(List<Schedules> allSchedule) {
        List<ScheduleResponse> allScheduleResponse = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            ScheduleResponse scheduleResponse = ScheduleResponse.build(schedules);
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }
    private List<SearchScheduleResponse> toListSearchScheduleResponse(Iterable<Schedules> allSchedule, Iterable<Routes> allRoute, Iterable<Airplanes> allAirplane) {
        List<SearchScheduleResponse> allSearchScheduleResponse = new ArrayList<>();
        for (Schedules schedules : allSchedule)
        {
            for (Routes routes : allRoute)
            {
                for (Airplanes airplanes : allAirplane)
                {
                    SearchScheduleResponse searchScheduleResponse = SearchScheduleResponse.build(schedules, routes, airplanes);
                    allSearchScheduleResponse.add(searchScheduleResponse);
                    break;
                }
                break;
            }

        }
        return allSearchScheduleResponse;
    }
}
