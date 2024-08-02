package com.urunsiyabend.flightscqrs.cq.query.impl.planetype;

import com.urunsiyabend.flightscqrs.cq.query.common.Query;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaneTypesQuery implements Query {
    private String manufacturer;
}
