package com.te.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.te.dto.ExternalWeatherResponse;
import com.te.dto.WeatherResponse;



import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherResponse getWeather(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", weatherApiUrl, city, apiKey);

        // Fetching data from the weather API
        ResponseEntity<ExternalWeatherResponse> response = restTemplate.getForEntity(url, ExternalWeatherResponse.class);

        System.out.println("Response: " + response);

        if (response.getStatusCode() == HttpStatus.OK) {
            ExternalWeatherResponse externalResponse = response.getBody();
            if (externalResponse != null) {
                return mapToWeatherResponse(externalResponse);
            }
        }

        throw new RuntimeException("Failed to fetch weather data");
    }

    private WeatherResponse mapToWeatherResponse(ExternalWeatherResponse externalResponse) {
        // Convert Unix timestamp to formatted date (yyyy-MM-dd HH:mm:ss)
        String formattedDate = convertToFormattedDate(externalResponse.getDt());

        return new WeatherResponse(
            externalResponse.getName(),
            externalResponse.getMain().getTemp(),
            externalResponse.getWeather().get(0).getDescription(),
            externalResponse.getMain().getHumidity(),
            formattedDate
        );
    }

    // Convert Unix timestamp to formatted date string
    private String convertToFormattedDate(long unixTimestamp) {
//    	System.out.println("LocalTime:"+LocalDateTime.now());
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        System.out.println("instant:"+instant);
        LocalDateTime dateTime = instant.atZone(ZoneOffset.UTC).toLocalDateTime();
        System.out.println("dateTime:"+dateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}


