package com.te.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalWeatherResponse {
    private String name; // City name
    private Main main;   // Temperature and humidity
    private List<Weather> weather; // Weather description
    private long dt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Main {
        private double temp;
        private int humidity;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Weather {
        private String description;
    }
}
