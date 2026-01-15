package ps_projekt.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
public class userJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(userJsonDataLoader.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public userJsonDataLoader(UserRepository userRepository, ObjectMapper objectMapper){
        this.userRepository=userRepository;
        this.objectMapper=objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if(userRepository.count()==0){
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/users.json")){
                Users allUsers = objectMapper.readValue(inputStream, Users.class);
                log.info("Reading {} users form JSON data and saving to local database", allUsers.users().size());
                userRepository.saveAll(allUsers.users());
            }
            catch (IOException e){
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
        else {
            log.info("Not loading contacts from JSON data because database contains data.");
        }
    }
}
