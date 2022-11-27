package com.binar.finalproject.BEFlightTicket.repository;

import com.binar.finalproject.BEFlightTicket.model.IDCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IdCardRepository extends JpaRepository<IDCard, String> {
    @Query(nativeQuery = true, value = "SELECT * FROM id_card i where i.traveler_id = :travelerId")
    List<IDCard> findAllIdCardByTravelerList(@Param("travelerId") UUID travelerId);
}
