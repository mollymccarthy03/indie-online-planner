package onlineplanner.controller;

import onlineplanner.entity.Temperature;

import java.time.LocalDateTime;

public class WeatherResponse {
    private String weatherText;
    private LocalDateTime localObservationDateTime;
    private Temperature temperature;

    // Getters and Setters
    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public LocalDateTime getLocalObservationDateTime() {
        return localObservationDateTime;
    }

    public void setLocalObservationDateTime(LocalDateTime localObservationDateTime) {
        this.localObservationDateTime = localObservationDateTime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weatherText='" + weatherText + '\'' +
                ", localObservationDateTime=" + localObservationDateTime +
                ", temperature=" + temperature +
                '}';
    }
}
