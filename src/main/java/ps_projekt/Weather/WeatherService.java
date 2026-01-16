package ps_projekt.Weather;

import org.springframework.web.client.RestClient;

public class WeatherService {
    public static String getCurrentWeather(Location location){
        RestClient defaultClient = RestClient.create();
        return defaultClient
                .get()
                .uri("https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longtitude}&current=temperature_2m,is_day,rain,wind_gusts_10m,showers,snowfall",
                        location.latitude,
                        location.longitude)
                .retrieve()
                .body(String.class);
    }
}
