package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.Airplanes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplanesRepository extends JpaRepository <Airplanes, String> {
}
