package com.ascertia.userserviceclient.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="operations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Operations {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name="status")
    private String status;

    @Column(name="error_message")
    private String errorMessage;

    @Column(name = "request_date_time")
    private LocalDateTime request_date_Time;

    @Column(name="end_point")
    private String endPoint;

    @Column(name = "request")
    private String request;

    @Column(name="response")
    private String response;

}
