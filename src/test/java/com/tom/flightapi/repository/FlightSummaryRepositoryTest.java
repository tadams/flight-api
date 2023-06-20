package com.tom.flightapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;
import com.tom.flightapi.model.FlightSummary;
import org.junit.jupiter.api.Test;

class FlightSummaryRepositoryTest {

    private FlightSummaryRepository repository = new FlightSummaryRepository();

    @Test
    void shouldFindFlightSummaryWithOriginDestination() {
        Optional<FlightSummary> flightSummary = repository.findFlights("IAH", "FLL");

        assertThat(flightSummary).isPresent();
        assertThat(flightSummary.get().getRoute()).isEqualTo("IAHFLL");
    }

    @Test
    void shouldFindFlightSummaryWithRoute() {
        Optional<FlightSummary> flightSummary = repository.findFlights("DFWIAH");

        assertThat(flightSummary).isPresent();
        assertThat(flightSummary.get().getRoute()).isEqualTo("DFWIAH");
    }

    @Test
    void shouldIncreaseRouteCost() {
        Optional<FlightSummary> originalSummary = repository.findFlights("DFWIAH");
        BigDecimal originalCost = originalSummary.get().cost();

        repository.updateFlightCost("DFWIAH");
        Optional<FlightSummary> updatedSummary = repository.findFlights("DFWIAH");

        assertThat(updatedSummary.get().cost()).isEqualTo(originalCost.add(BigDecimal.TEN));
    }

}