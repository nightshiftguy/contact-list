package ps_projekt.Weather;

import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;

public class LocationService {
    public static Location getLocation(String city){
        RestClient defaultClient = RestClient.create();
        JsonNode root = defaultClient
                .get()
                .uri("https://geocoding-api.open-meteo.com/v1/search?name={city}&count=1&language=en&format=json", city)
                .retrieve()
                .body(JsonNode.class);
        JsonNode first = root.path("results").get(0);

        Location location = new Location();
        location.latitude = first.path("latitude").asString();
        location.longitude = first.path("longitude").asString();
        return location;
    }
}
