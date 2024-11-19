package onlineplanner.controller;

import onlineplanner.entity.Temperature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeatherClient {

    private static final Logger logger = LogManager.getLogger(WeatherClient.class);
    private final String myApiKey = "DI0qExiNX2eJOQu50Lnak5gzk8VSaQxN";

    public WeatherResponse getCurrentConditions(String locationKey) throws Exception {
        String urlString = "https://dataservice.accuweather.com/currentconditions/v1/" + locationKey + "?apikey=" + myApiKey;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        WeatherResponse weatherResponse = new WeatherResponse();

        try {
            // Parse JSON array
            JSONArray jsonArray = new JSONArray(response.toString());
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            // Map JSON data to POJO
            weatherResponse.setWeatherText(jsonObject.optString("WeatherText", "No weather info available"));

            // Parse the LocalObservationDateTime safely
            String dateTimeString = jsonObject.getString("LocalObservationDateTime");
            try {
                OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeString);
                weatherResponse.setLocalObservationDateTime(offsetDateTime.toLocalDateTime());
            } catch (DateTimeParseException e) {
                // Log error and set a default date/time
                logger.error("DateTime parsing error: {}", e.getMessage());
                weatherResponse.setLocalObservationDateTime(LocalDateTime.now());
            }

            // Continue with parsing temperature
            JSONObject temperatureObject = jsonObject.getJSONObject("Temperature");
            JSONObject metricObject = temperatureObject.getJSONObject("Metric");

            Temperature.Metric metric = new Temperature.Metric();
            metric.setValue(metricObject.getDouble("Value"));
            metric.setUnit(metricObject.getString("Unit"));

            Temperature temperature = new Temperature();
            temperature.setMetric(metric);
            weatherResponse.setTemperature(temperature);

        } catch (JSONException e) {
            // Exception
            logger.error("Failed to parse weather response: {}", e.getMessage());
            return null;
        }

        return weatherResponse;
    }

}
