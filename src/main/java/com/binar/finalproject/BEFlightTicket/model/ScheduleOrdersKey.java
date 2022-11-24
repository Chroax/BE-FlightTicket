package com.binar.finalproject.BEFlightTicket.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class ScheduleOrdersKey implements Serializable {
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "schedule_id")
    private UUID scheduleId;
}
