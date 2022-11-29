package com.binar.finalproject.BEFlightTicket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private UUID scheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "departure_date", nullable = false, columnDefinition="DATE")
    private LocalDate departureDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "arrival_date", nullable = false, columnDefinition="DATE")
    private LocalDate arrivalDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "price", nullable = false, precision = 2)
    private Float price;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="airplane_name", nullable = false)
    private Airplanes airplanesSchedules;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "airplane_type", nullable = false)
    private Airplanes airplaneTypeSchedule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="route_id", nullable = false)
    private Routes routesSchedules;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_city", nullable = false)
    private Routes departureCitySchedule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_city", nullable = false)
    private Routes arrivalCitySchedule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport", nullable = false)
    private Routes departureAirportSchedule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_airport", nullable = false)
    private Routes arrivalAirportSchedule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_terminal", nullable = false)
    private Routes departureTerminalSchedule;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_terminal", nullable = false)
    private Routes arrivalTerminalSchedule;

    @OneToMany(mappedBy = "schedules", cascade = CascadeType.ALL)
    private Set<ScheduleOrders> scheduleOrders;
}
