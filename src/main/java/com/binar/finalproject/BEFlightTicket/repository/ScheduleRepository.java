package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedules, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s where s.airplane_name = :airplaneName")
    List<Schedules> getAllAirplaneSchedule(@Param("airplaneName") String airplaneName);
    @Query(nativeQuery = true, value = "SELECT * FROM schedules s where s.route_id = :routeId")
    List<Schedules> getAllRouteSchedule(@Param("routeId") UUID routeId);
    @Query("SELECT s FROM Schedules s WHERE LOWER(s.departureCitySchedule) LIKE LOWER(:departureCitySchedule) " +
            "AND LOWER(s.arrivalCitySchedule) LIKE LOWER(:arrivalCitySchedule) AND LOWER(s.departureDate) LIKE LOWER(:departureDate) ")
    List<Schedules> findAirplaneTicketSchedule(@Param("departureCity") String departureCity,
                                               @Param("arrivalCity") String arrivalCity,
                                               @Param("departureDate") LocalDate departureDate);
}
