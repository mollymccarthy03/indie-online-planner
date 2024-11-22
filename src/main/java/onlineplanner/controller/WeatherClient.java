package onlineplanner.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WeatherClient {

    private static final Logger logger = LogManager.getLogger(WeatherClient.class);
    private static final String API_URL = "https://dataservice.accuweather.com/currentconditions/v1/";

    private String apiKey;

    public WeatherClient() {
        loadApiKey();
    }

    private void loadApiKey() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
            this.apiKey = properties.getProperty("accuweather.apikey");
        } catch (IOException e) {
            logger.error("Error loading API key: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WeatherResponse getCurrentConditions(String locationKey) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_URL + locationKey)
                .queryParam("apikey", apiKey);

        try {
            String jsonResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            WeatherResponse[] responses = mapper.readValue(jsonResponse, WeatherResponse[].class);
            return responses.length > 0 ? responses[0] : null;
        } catch (Exception e) {
            logger.error("Error retrieving weather data: {}", e.getMessage());
            return null;
        }
    }
}
