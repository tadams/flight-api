package com.tom.flightapi;

import static org.assertj.core.api.Assertions.assertThat;

import com.tom.flightapi.controller.FlightSummaryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightApiApplicationTests {

    @Autowired
    private FlightSummaryController controller;

	@Test
	void contextLoads() {
        assertThat(controller).isNotNull();
	}

}
