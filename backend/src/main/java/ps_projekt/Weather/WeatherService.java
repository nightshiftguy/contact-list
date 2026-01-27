package ps_projekt.Weather;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.JsonNode;

public class WeatherService {
    public static JsonNode getCurrentWeather(Location location){
        RestClient defaultClient = RestClient.create();
        try {
            return defaultClient
                    .get()
                    .uri("https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longtitude}&current=temperature_2m,is_day,rain,wind_gusts_10m,showers,snowfall",
                            location.latitude,
                            location.longitude)
                    .retrieve()
                    .body(JsonNode.class);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error fetching weather data");

        }
    }
}
