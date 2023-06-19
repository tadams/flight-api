package com.tom.flightapi.controller;

import java.util.Optional;
import com.tom.flightapi.command.FlightSummaryFindCommand;
import com.tom.flightapi.command.Invoker;
import com.tom.flightapi.model.FlightSummary;
import com.tom.flightapi.service.FlightSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight-summary")
public class FlightSummaryController {

    @Autowired
    private FlightSummaryService flightSummaryService;

    @Autowired
    private Invoker invoker;

    @GetMapping
    public ResponseEntity<FlightSummary> findFlightSummary(
            @RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination) {
        Optional<FlightSummary> flightSummary = flightSummaryService.findFlightSummary(origin, destination);
        if (flightSummary.isPresent()) {
            return ResponseEntity.ok(flightSummary.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("cmd")
    public ResponseEntity<FlightSummary> findFlightSummaryCommand(
            @RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination) {

        FlightSummaryFindCommand command = new FlightSummaryFindCommand(flightSummaryService, origin, destination);
        invoker.run(command);

        if (command.flightSummary().isPresent()) {
            return ResponseEntity.ok(command.flightSummary().get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
