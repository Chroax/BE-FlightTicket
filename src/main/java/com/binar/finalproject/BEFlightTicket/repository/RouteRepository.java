package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Routes, UUID> {
    @Query("SELECT r FROM Routes r WHERE LOWER(r.departureCity) LIKE LOWER(:departureCity) AND LOWER(r.arrivalCity) LIKE LOWER(:arrivalCity) ")
    List<Routes> findRouteByDepartureCityAndArrivalCity(@Param("departureCity") String departureCity, @Param("arrivalCity") String arrivalCity);
    @Query("SELECT r FROM Routes r WHERE LOWER(r.departureAirport) LIKE LOWER(:departureAirport) AND LOWER(r.arrivalAirport) LIKE LOWER(:arrivalAirport) ")
    List<Routes> findRouteByDepartureAndArrivalAirport(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport);
}
