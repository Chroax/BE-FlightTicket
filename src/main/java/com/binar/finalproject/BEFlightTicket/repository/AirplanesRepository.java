package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import com.binar.finalproject.BEFlightTicket.model.Routes;
import com.binar.finalproject.BEFlightTicket.model.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AirplanesRepository extends JpaRepository <Airplanes, String> {
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate")
    List<Airplanes> searchAirplaneTicket(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);

    @Query("SELECT a FROM Airplanes a INNER JOIN Schedules s ON s.airplanesSchedules = a.airplaneName INNER JOIN Routes r ON s.routesSchedules = r.routeId where r.departureAirport = :departureAirport and r.arrivalAirport = :arrivalAirport and s.departureDate = :departureDate ORDER BY s.price ASC ")
    Page<Airplanes> searchAirplaneTicketOrderByLowerPrice(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.price DESC ;")
    Page<Airplanes> searchAirplaneTicketOrderByHigherPrice(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.departure_time ASC;")
    List<Airplanes> searchScheduleTicketByEarliestDepartureTime(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.departure_time DESC ;")
    List<Airplanes> searchAirplaneTicketByLatestDepartureTime(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.arrival_time ASC;")
    List<Airplanes> searchScheduleTicketByEarliestArrivalTime(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);
    @Query(nativeQuery = true, value = "SELECT*FROM schedules s INNER JOIN routes r ON s.route_id = r.route_id INNER JOIN airplanes a ON s.airplane_name = a.airplane_name where r.departure_airport = :departureAirport and r.arrival_airport = :arrivalAirport and s.departure_date = :departureDate ORDER BY s.arrival_time DESC ;")
    List<Airplanes> searchScheduleTicketByLatestArrivalTime(@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate);
    @Query("SELECT a FROM Airplanes a INNER JOIN Schedules s ON s.airplanesSchedules = a.airplaneName INNER JOIN Routes r ON s.routesSchedules = r.routeId where r.departureAirport = :departureAirport and r.arrivalAirport = :arrivalAirport and s.departureDate = :departureDate")
    Page<Airplanes> searchTicket (@Param("departureAirport") String departureAirport, @Param("arrivalAirport") String arrivalAirport, @Param("departureDate") LocalDate departureDate, Pageable pageable);
}
