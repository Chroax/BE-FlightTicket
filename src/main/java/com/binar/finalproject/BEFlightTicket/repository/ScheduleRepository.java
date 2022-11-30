package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.dto.SearchScheduleResponse;
import com.binar.finalproject.BEFlightTicket.model.Schedules;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedules, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s where s.airplane_name = :airplaneName")
    List<Schedules> getAllAirplaneSchedule(@Param("airplaneName") String airplaneName);
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s where s.route_id = :routeId")
    List<Schedules> getAllRouteSchedule(@Param("routeId") UUID routeId);

    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate")
    List<Schedules> searchScheduleTicket(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);

    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.price ASC;")
    List<Schedules> searchScheduleTicketOrderByLowerPrice(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);
}
