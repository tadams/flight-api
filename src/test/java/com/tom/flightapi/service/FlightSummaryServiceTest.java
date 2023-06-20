package com.tom.flightapi.service;

import static org.mockito.Mockito.verify;

import com.tom.flightapi.repository.FlightSummaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightSummaryServiceTest {

    @InjectMocks
    private FlightSummaryService flightSummaryService = new FlightSummaryService();

    @Mock
    private FlightSummaryRepository flightSummaryRepository;

    @Test
    void shouldCallServiceForFlightSummary() {
        flightSummaryService.findFlightSummary("Origin", "Destination");

        verify(flightSummaryRepository).findFlights("Origin", "Destination");
    }

    @Test
    void shouldCallServiceForUpdateFlightCost() {
        flightSummaryService.updateFlightCost("Route");

        verify(flightSummaryRepository).updateFlightCost("Route");
    }

}