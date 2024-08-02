package com.urunsiyabend.flightscqrs.planetype;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaneTypeDTO {
    private Long id;
    private String manufacturer;
    private String model;
    private Integer capacity;
}
