package com.tom.flightapi.service;

import java.util.Optional;
import com.tom.flightapi.model.FlightSummary;
import com.tom.flightapi.repository.FlightSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightSummaryService {

    @Autowired
    private FlightSummaryRepository repository;

    public Optional<FlightSummary> findFlightSummary(String origin, String destination) {
        return repository.findFlights(origin, destination);
    }

    public void updateFlightCost(String route) {
        repository.updateFlightCost(route);
    }

}
