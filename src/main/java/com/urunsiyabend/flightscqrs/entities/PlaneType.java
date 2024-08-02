package com.urunsiyabend.flightscqrs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* CREATE TABLE IF NOT EXISTS plane_type
(
    id           SERIAL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model        VARCHAR(255) NOT NULL,
    capacity     INTEGER      NOT NULL
);
* */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plane_type")
public class PlaneType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer capacity;
}
