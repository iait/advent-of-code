package com.adventofcode;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SonarSweepTest {

    @Test
    void testPartOneWithEmptyInput() {

        String input = "";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countIncreases(is);
        assertThat(count, equalTo(0));
    }

    @Test
    void testPartOneWithSingleNumberInput() {

        String input = "199";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countIncreases(is);
        assertThat(count, equalTo(0));
    }

    @Test
    void testPartOneWithMultipleNumberInput() {

        String input = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """;
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countIncreases(is);
        assertThat(count, equalTo(7));
    }

    @Test
    void testPartTwoWithEmptyInput() {

        String input = "";
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countWindowIncreases(is, 3);
        assertThat(count, equalTo(0));
    }

    @Test
    void testPartTwoWithOneWindowInput() {

        String input = """
                199
                200
                208
                """;
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countWindowIncreases(is, 3);
        assertThat(count, equalTo(0));
    }

    @Test
    void testPartTwoWithMultipleWindowInput() {

        String input = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """;
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        int count = SonarSweep.countWindowIncreases(is, 3);
        assertThat(count, equalTo(5));
    }

}
