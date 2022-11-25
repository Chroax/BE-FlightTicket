package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelerListRepository extends JpaRepository<TravelerList, UUID> {
}
