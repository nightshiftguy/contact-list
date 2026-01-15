package ps_projekt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ps_projekt.Contact.ContactRepository;
import ps_projekt.Contact.Contacts;
import ps_projekt.User.UserRepository;
import ps_projekt.User.Users;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class jsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(jsonDataLoader.class);
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

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
        if(contactRepository.count()==0){
            try (InputStream inputStream =TypeReference.class.getResourceAsStream("/data/contacts.json")){
                Contacts allContacts = objectMapper.readValue(inputStream, Contacts.class);
                log.info("Reading {} contacts form JSON data and saving to local database", allContacts.contacts().size());
                contactRepository.saveAll(allContacts.contacts());
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
