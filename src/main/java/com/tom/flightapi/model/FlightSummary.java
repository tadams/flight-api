package com.tom.flightapi.model;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

public record FlightSummary(
        List<String> airlines,
        String origin,
        String destination,
        Integer stops,
        Integer lengthHrs,
        Integer lengthMin,
        BigDecimal cost
) {
    public FlightSummary newCost(BigDecimal newCost) {
        return new FlightSummary(airlines, origin, destination, stops, lengthHrs, lengthMin, newCost);
    }


    @JsonIgnore
    public String getRoute() {
        return this.origin + this.destination;
    }
}
