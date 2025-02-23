package com.te.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {

	private String city;
    private double temperature;
    private String description;
    private int humidity;
    private String date;
}
