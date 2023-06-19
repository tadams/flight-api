package com.tom.flightapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tom.flightapi.FlightApiApplication;
import com.tom.flightapi.model.FlightSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FlightApiApplication.class})
@WebAppConfiguration
class FlightSummaryControllerTest {

    private static final String GET_URL = "/patient-statuses";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void given() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shouldFindFlightSummary() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/flight-summary")
                                                                 .queryParam("origin", "IAH")
                                                                 .queryParam("destination", "FLL"))
                                  .andExpect(status().isOk())
                                  .andReturn();

        FlightSummary flightSummary = objectMapper.readValue(result.getResponse().getContentAsString(),
                                                             new TypeReference<>() {
                                                             });
        assertThat(flightSummary.getRoute()).isEqualTo("IAHFLL");
    }

    @Test
    public void shouldNotFindFlightSummary() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/flight-summary")
                                              .queryParam("origin", "ORD")
                                              .queryParam("destination", "MDW"))
               .andExpect(status().isNotFound());
    }

}