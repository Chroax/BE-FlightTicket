package com.binar.finalproject.BEFlightTicket.repository;
import com.binar.finalproject.BEFlightTicket.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatRepository extends JpaRepository<Seats, Integer> {
    @Query("SELECT s FROM Seats s WHERE LOWER(s.seatNumber) LIKE LOWER(:seatNumber)")
    Seats findByNumber(@Param("seatNumber") String roleName);
}
