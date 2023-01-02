package com.binar.finalproject.BEFlightTicket.dummy;

import com.binar.finalproject.BEFlightTicket.dto.RouteRequest;
import com.binar.finalproject.BEFlightTicket.dto.ScheduleRequest;
import com.binar.finalproject.BEFlightTicket.dto.UserRequest;
import com.binar.finalproject.BEFlightTicket.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataDummySchedule {
    private final List<Schedules> DATA_SCHEDULE = new ArrayList<>();
    private final List<ScheduleRequest> DATA_SCHEDULE_REQUEST = new ArrayList<>();

    private final List<Routes> DATA_ROUTE = new ArrayList<>();
    private final List<RouteRequest> DATA_ROUTES_REQUEST = new ArrayList<>();

    Airplanes airplanes = new Airplanes();
    Routes routes = new Routes();

    public DataDummySchedule(){
        DataDummyAirport dataDummyAirport = new DataDummyAirport();
        ScheduleRequest scheduleRequest1 = new ScheduleRequest();
        scheduleRequest1.setDepartureDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest1.setArrivalDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest1.setDepartureTime(LocalTime.of(10,30,00));
        scheduleRequest1.setArrivalTime(LocalTime.of(12,30,00));
        scheduleRequest1.setPrice(1500000F);
        scheduleRequest1.setStatus("ON TIME");

        DataDummyAirport dataDummyAirport2 = new DataDummyAirport();
        ScheduleRequest scheduleRequest2 = new ScheduleRequest();
        scheduleRequest2.setDepartureDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest2.setArrivalDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest2.setDepartureTime(LocalTime.of(12,30,00));
        scheduleRequest2.setArrivalTime(LocalTime.of(14,30,00));
        scheduleRequest2.setPrice(1200000F);
        scheduleRequest2.setStatus("ON TIME");

        DataDummyAirport dataDummyAirport3 = new DataDummyAirport();
        ScheduleRequest scheduleRequest3 = new ScheduleRequest();
        scheduleRequest3.setDepartureDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest3.setArrivalDate(LocalDate.ofEpochDay(2023-02-27));
        scheduleRequest3.setDepartureTime(LocalTime.of(15,30,00));
        scheduleRequest3.setArrivalTime(LocalTime.of(17,30,00));
        scheduleRequest3.setPrice(1300000F);
        scheduleRequest3.setStatus("ON TIME");
        
    }
    public void DataDummyRoute(){
        RouteRequest routeRequest1 = new RouteRequest();
        routeRequest1.setDepartureCity("Jakarta");
        routeRequest1.setArrivalCity("Denpasar");
        routeRequest1.setDepartureAirport("Soekarno Hatta");
        routeRequest1.setArrivalAirport("Ngurah Rai");
        routeRequest1.setDepartureTerminal("1B");
        routeRequest1.setArrivalTerminal("1A");

        RouteRequest routeRequest2 = new RouteRequest();
        routeRequest2.setDepartureCity("Denpasar");
        routeRequest2.setArrivalCity("Jakarta");
        routeRequest2.setDepartureAirport("Ngurah Rai");
        routeRequest2.setArrivalAirport("Soekarno Hatta");
        routeRequest2.setDepartureTerminal("1A");
        routeRequest2.setArrivalTerminal("1B");

        Routes routes1 = routeRequest1.toRoutes();
        Routes routes2 = routeRequest2.toRoutes();

        DATA_ROUTES_REQUEST.add(routeRequest1);
        DATA_ROUTES_REQUEST.add(routeRequest2);

        DATA_ROUTE.add(routes1);
        DATA_ROUTE.add(routes2);
    }
}
