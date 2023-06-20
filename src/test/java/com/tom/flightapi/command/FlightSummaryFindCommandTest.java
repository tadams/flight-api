package com.tom.flightapi.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import com.tom.flightapi.model.FlightSummary;
import com.tom.flightapi.service.FlightSummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightSummaryFindCommandTest {

    @Mock
    private FlightSummaryService flightSummaryService;

    private final FlightSummary flightSummary = new FlightSummary(List.of("UA"),
                                                                  "Origin",
                                                                  "Destination",
                                                                  0, 1, 5, new BigDecimal("100.00"));

    @Test
    void shouldFindFlightSummary() {
        when(flightSummaryService.findFlightSummary(anyString(), anyString()))
                .thenReturn(Optional.of(flightSummary));
        FlightSummaryFindCommand command = new FlightSummaryFindCommand(flightSummaryService,
                                                                        "Origin", "Destination");
        command.execute();

        assertThat(command.getFlightSummary()).isSameAs(flightSummary);
    }

    @Test
    void shouldReturnEmptyOptionalWhenFlightNotFound() {
        FlightSummaryFindCommand command = new FlightSummaryFindCommand(flightSummaryService,
                                                                        "Origin", "Destination");
        command.execute();

        assertThat(command.getFlightSummary()).isNull();
        assertThat(command.flightSummary()).isEmpty();
    }

}