package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CountriesRepository extends JpaRepository<Countries, String> {

    @Query("SELECT r FROM Countries r WHERE LOWER(r.countryName) LIKE LOWER(:countryName)")
    Countries findByCountriesName(@Param("countryName") String countryName);
}
