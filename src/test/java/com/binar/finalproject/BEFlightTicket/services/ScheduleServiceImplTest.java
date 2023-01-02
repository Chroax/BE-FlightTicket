package com.binar.finalproject.BEFlightTicket.services;

import com.binar.finalproject.BEFlightTicket.dto.ScheduleRequest;
import com.binar.finalproject.BEFlightTicket.model.*;
import com.binar.finalproject.BEFlightTicket.repository.*;
import com.binar.finalproject.BEFlightTicket.service.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class ScheduleServiceImplTest {
    @InjectMocks
    ScheduleServiceImpl scheduleService;

    @Mock
    AirplanesRepository airplanesRepository;

    @Mock
    RouteRepository routeRepository;

    @Mock
    ScheduleRepository scheduleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test [Positive] Create New Schedule")
    void testPositiveAddSchedule() {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setDepartureDate(LocalDate.ofEpochDay(2023-2-27));
        scheduleRequest.setArrivalDate(LocalDate.ofEpochDay(2023-2-27));
        scheduleRequest.setDepartureTime(LocalTime.of(12, 30, 0));
        scheduleRequest.setArrivalTime(LocalTime.of(14, 30, 0));
        scheduleRequest.setPrice(1500000F);
        scheduleRequest.setStatus("ON TIME");
        scheduleRequest.setAirplaneName("JET123");
        scheduleRequest.setRouteId(UUID.randomUUID());

        Schedules schedules = Schedules.builder()
                .scheduleId(UUID.randomUUID())
                .departureDate(LocalDate.ofEpochDay(2023- 2 -27))
                .arrivalDate(LocalDate.ofEpochDay(2023- 2 -27))
                .departureTime(LocalTime.of(12, 30, 0))
                .arrivalTime(LocalTime.of(12, 30, 0))
                .price(1500000F)
                .status("ON TIME")
                .build();

        Airplanes airplanes = Airplanes.builder()
                .airplaneName("JET123")
                .airplaneType("Airbus")
                .build();

        Routes routes = Routes.builder()
                .routeId(UUID.randomUUID())
                .departureCity("INDONESIA")
                .arrivalCity("INDONESIA")
                .departureAirport("Soekarno Hatta")
                .arrivalAirport("Ngurah Rai")
                .departureTerminal("1A")
                .arrivalTerminal("1B")
                .build();

        Mockito.when(airplanesRepository.findById(scheduleRequest.getAirplaneName())).thenReturn(Optional.of(airplanes));
        Mockito.when(routeRepository.findById(scheduleRequest.getRouteId())).thenReturn(Optional.of(routes));
        Mockito.when(scheduleRepository.save(ArgumentMatchers.any(Schedules.class))).thenReturn(schedules);

        scheduleService.addSchedule(scheduleRequest);
        Mockito.verify(airplanesRepository).findById(scheduleRequest.getAirplaneName());
        Mockito.verify(routeRepository).findById(scheduleRequest.getRouteId());
        Mockito.verify(scheduleRepository).save(ArgumentMatchers.any(Schedules.class));
    }

    @Test
    @DisplayName("Test [Positive] Update Schedule")
    void testPositiveUpdateSchedule() {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        scheduleRequest.setDepartureDate(LocalDate.ofEpochDay(2023- 2 -27));
        scheduleRequest.setArrivalDate(LocalDate.ofEpochDay(2023- 2 -27));
        scheduleRequest.setDepartureTime(LocalTime.of(12, 30, 0));
        scheduleRequest.setArrivalTime(LocalTime.of(14, 30, 0));
        scheduleRequest.setPrice(1500000F);
        scheduleRequest.setStatus("ON TIME");
        scheduleRequest.setAirplaneName("JET123");
        scheduleRequest.setRouteId(UUID.randomUUID());

        Schedules schedules = Schedules.builder()
                .scheduleId(UUID.randomUUID())
                .departureDate(LocalDate.ofEpochDay(2023- 2 -27))
                .arrivalDate(LocalDate.ofEpochDay(2023- 2 -27))
                .departureTime(LocalTime.of(12, 30, 0))
                .arrivalTime(LocalTime.of(12, 30, 0))
                .price(1500000F)
                .status("ON TIME")
                .build();

        Airplanes airplanes = Airplanes.builder()
                .airplaneName("JET123")
                .airplaneType("Airbus")
                .build();

        Routes routes = Routes.builder()
                .routeId(UUID.randomUUID())
                .departureCity("INDONESIA")
                .arrivalCity("INDONESIA")
                .departureAirport("Soekarno Hatta")
                .arrivalAirport("Ngurah Rai")
                .departureTerminal("1A")
                .arrivalTerminal("1B")
                .build();

        UUID scheduleId = UUID.randomUUID();

        Mockito.when(scheduleRepository.findById(scheduleId)).thenReturn(Optional.of(schedules));
        Mockito.when(airplanesRepository.findById(scheduleRequest.getAirplaneName())).thenReturn(Optional.of(airplanes));
        Mockito.when(routeRepository.findById(scheduleRequest.getRouteId())).thenReturn(Optional.of(routes));
        Mockito.when(scheduleRepository.saveAndFlush(ArgumentMatchers.any(Schedules.class))).thenReturn(schedules);

        scheduleService.updateSchedule(scheduleRequest, scheduleId);
        Mockito.verify(scheduleRepository).findById(scheduleId);
        Mockito.verify(airplanesRepository).findById(scheduleRequest.getAirplaneName());
        Mockito.verify(airplanesRepository).findById(scheduleRequest.getAirplaneName());
        Mockito.verify(scheduleRepository).saveAndFlush(ArgumentMatchers.any(Schedules.class));
    }
    

}
