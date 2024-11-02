package onlineplanner.util;

import onlineplanner.controller.WeatherClient;
import onlineplanner.controller.WeatherResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherClientTest {

    @Test
    void getCurrentConditions_ShouldReturnWeatherResponse() throws Exception {
        // Test with a known location key (use a valid API key and location key for a real test)
        WeatherClient client = new WeatherClient();
        WeatherResponse response = client.getCurrentConditions("331530");

        assertNotNull(response);
        System.out.println("Weather: " + response.getWeatherText());
        System.out.println("Temperature: " + response.getTemperature().getMetric().getValue());
    }
}
