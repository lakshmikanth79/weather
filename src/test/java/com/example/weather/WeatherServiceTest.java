package com.example.weather;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {

    @Test
    public void testValidCityReturnsTemperature() {
        WeatherService service = new WeatherService();
        String result = service.getWeather("chennai");

        assertNotEquals("wrong entry", result);
        assertTrue(result.contains("temperature:"), "Expected temperature in output");
    }

    @Test
    public void testInvalidCityReturnsWrongEntry() {
        WeatherService service = new WeatherService();
        String result = service.getWeather("abcdinvalidcity123");

        assertEquals("wrong entry", result);
    }
}
