package com.urunsiyabend.flightscqrs.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaneTypeResponse {
    private String id;
    private String manufacturer;
    private String model;
    private int capacity;
}
