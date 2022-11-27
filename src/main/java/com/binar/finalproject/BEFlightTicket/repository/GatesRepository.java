package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Gates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GatesRepository extends JpaRepository<Gates, Integer> {

    @Query("SELECT g FROM Gates g WHERE LOWER(g.gateName) LIKE LOWER(:gateName)")
    Gates findByGatesName(@Param("gateName") String gateName);
}