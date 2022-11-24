package com.binar.finalproject.BEFlightTicket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airplanes")
public class Airplanes {
    @Id
    @Column(name = "airplane_name")
    private String airplaneName;

    @Column(name = "airplane_type", nullable = false)
    private String airplaneType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modified_at", insertable = false)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
}
