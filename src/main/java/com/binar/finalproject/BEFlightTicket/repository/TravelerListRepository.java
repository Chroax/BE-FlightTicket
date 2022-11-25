package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.TravelerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TravelerListRepository extends JpaRepository<TravelerList, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM traveler_list t where t.user_id = :userId")
    List<TravelerList> findAllTravelerListByUser(@Param("userId") UUID userId);
}
