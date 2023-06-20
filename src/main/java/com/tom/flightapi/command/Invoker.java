package com.tom.flightapi.command;

import com.tom.flightapi.service.FlightSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Invoker {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FlightSummaryService flightSummaryService;

    public void run(Command command) {
        command.execute();
        logCommand(command);
        increaseRouteCost(command);
    }

    private void logCommand(Command command) {
        if (logger.isInfoEnabled()) {
            logger.info(command.toJson());
        }
    }

    private void increaseRouteCost(Command command) {
        command.getRoute().ifPresent(route -> flightSummaryService.updateFlightCost(route));
    }
}
