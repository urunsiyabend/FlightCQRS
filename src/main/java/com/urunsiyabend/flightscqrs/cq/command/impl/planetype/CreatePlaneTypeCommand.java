package com.urunsiyabend.flightscqrs.cq.command.impl.planetype;

import com.urunsiyabend.flightscqrs.cq.command.common.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlaneTypeCommand implements Command {
    private String manufacturer;
    private String model;
    private Integer capacity;
}
