package contact_list.config;

import com.resend.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {
  @Value("${spring.mail.api_key}")
  private String apiKey;

  @Bean
  public Resend resend() {
    return new Resend(apiKey);
  }
}
