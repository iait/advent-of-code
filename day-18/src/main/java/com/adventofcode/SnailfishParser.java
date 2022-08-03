package com.adventofcode;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class SnailfishParser {

    public static SnailfishNumber parse(String input) {

        ObjectMapper om = new ObjectMapper();
        SimpleModule sm = new SimpleModule("SnailfishNumberModule", new Version(1, 0, 0, null, null, null));
        sm.addDeserializer(SnailfishNumber.class, new SnailfishNumberDeserializer());
        om.registerModule(sm);
        try {
            return om.readValue(input, SnailfishNumber.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        }
    }
}
