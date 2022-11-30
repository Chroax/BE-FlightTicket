package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seats, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM seats s where s.airplane_name = :airplaneName")
    List<Seats> findAllSeatsByAirplaneName(@Param("airplaneName") String airplaneName);
}
