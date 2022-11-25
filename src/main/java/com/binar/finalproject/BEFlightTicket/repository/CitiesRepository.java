package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, String> {

    @Query("SELECT u FROM Cities u WHERE LOWER(u.cityName) LIKE LOWER(:cityName)")
    Cities findByCityName(@Param("cityName") String cityName);
}
