package com.tom.flightapi.command;

import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tom.flightapi.model.FlightSummary;
import com.tom.flightapi.service.FlightSummaryService;

public class FlightSummaryFindCommand implements Command {

    private final FlightSummaryService flightSummaryService;
    private final String origin;
    private final String destination;
    private FlightSummary flightSummary;

    public FlightSummaryFindCommand(FlightSummaryService flightSummaryService,
                                    String origin,
                                    String destination) {
        this.flightSummaryService = flightSummaryService;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void execute() {
        this.flightSummary = flightSummaryService.findFlightSummary(origin, destination)
                                                 .orElse(null);
    }

    @Override
    public Optional<String> getRoute() {
        return Optional.of(origin + destination);
    }

    @JsonIgnore
    public Optional<FlightSummary> flightSummary() {
        return Optional.ofNullable(flightSummary);
    }

    public FlightSummary getFlightSummary() {
        return flightSummary;
    }
}
