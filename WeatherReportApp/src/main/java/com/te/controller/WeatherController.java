package com.te.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.dto.WeatherResponse;
import com.te.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = {"null", "*"}) 
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/report")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam(name="city") String city) {
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        return ResponseEntity.ok(weatherResponse);
    }
}

