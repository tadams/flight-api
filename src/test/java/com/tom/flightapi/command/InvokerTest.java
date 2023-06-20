package com.tom.flightapi.command;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import com.tom.flightapi.service.FlightSummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InvokerTest {

    @InjectMocks
    private Invoker invoker = new Invoker();

    @Mock
    private FlightSummaryService flightSummaryService;

    @Mock
    private Command command;

    @Test
    void shouldLogAndIncreaseCost() {
        when(command.getRoute()).thenReturn(Optional.of("Route"));

        invoker.run(command);

        verify(command).execute();
        verify(flightSummaryService).updateFlightCost("Route");
    }

    @Test
    void shouldNotIncreaseCostWhenNoRouteForCommand() {
        when(command.getRoute()).thenReturn(Optional.empty());

        invoker.run(command);

        verify(command).execute();
        verifyNoInteractions(flightSummaryService);
    }

}