package com.tom.flightapi.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import com.tom.flightapi.model.FlightSummary;
import org.springframework.stereotype.Repository;

@Repository
public class FlightSummaryRepository {

    private Map<String, FlightSummary> flightData = new ConcurrentHashMap<>();

    public FlightSummaryRepository() {
        buildRepository();
    }

    private void buildRepository() {
        Stream.of(new FlightSummary(List.of("UA", "AA"), "IAH", "FLL", 0, 2, 15, new BigDecimal("105.00")),
                  new FlightSummary(List.of("UA", "AA"), "DFW", "FLL", 0, 2, 45, new BigDecimal("125.00")),
                  new FlightSummary(List.of("UA", "AA", "DL"), "SFO", "FLL", 0, 4, 15, new BigDecimal("575.00")),
                  new FlightSummary(List.of("UA", "AA", "WN"), "DFW", "IAH", 0, 0, 55, new BigDecimal("85.00")))
              .forEach(flightSummary -> this.flightData.put(flightSummary.getRoute(), flightSummary));
    }

    public Optional<FlightSummary> findFlights(String route) {
        return Optional.ofNullable(flightData.get(route));
    }

    public Optional<FlightSummary> findFlights(String origin, String destination) {
        return Optional.ofNullable(flightData.get(origin + destination));
    }

    public void updateFlightCost(String route) {
        Optional<FlightSummary> flightSummary = findFlights(route);
        if (flightSummary.isPresent()) {
            var flight = flightSummary.get();
            var updatedFlightSummary = flightSummary.get().newCost(flight.cost().add(BigDecimal.TEN));
            flightData.put(route, updatedFlightSummary);
        }
    }
}
