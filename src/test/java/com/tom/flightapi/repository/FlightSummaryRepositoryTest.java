package com.tom.flightapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import com.tom.flightapi.model.FlightSummary;
import org.junit.jupiter.api.Test;

class FlightSummaryRepositoryTest {

    private FlightSummaryRepository repository = new FlightSummaryRepository();

    @Test
    public void shouldFindFlightSummary() {
        Optional<FlightSummary> flightSummary = repository.findFlights("IAH", "FLL");

        assertThat(flightSummary).isPresent();
        assertThat(flightSummary.get().getRoute()).isEqualTo("IAHFLL");
    }

}