package ps_projekt.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.resend.*;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfiguration {
    @Value("${spring.mail.api_key}")
    private String apiKey;

    @Bean
    public Resend resend() {
        return new Resend(apiKey);
    }
}
