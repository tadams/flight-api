package com.tom.flightapi.command;

import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Command {

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    void execute();

    Optional<String> getRoute();

    default String toJson() {
        try {
            return OBJECT_MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error creating json";
        }
    }

    default String getCommandName() {
        return this.getClass().getSimpleName();
    }
}
