package ps_projekt.Weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("api/weather")
public class WeatherController {
    @GetMapping("")
    public JsonNode getCurrentWeather(@RequestParam String city) {
        Location location = LocationService.getLocation(city);
        return WeatherService.getCurrentWeather(location);
    }

}
