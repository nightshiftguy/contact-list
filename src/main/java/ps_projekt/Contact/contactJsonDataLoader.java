package ps_projekt.Contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Component
public class contactJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(contactJsonDataLoader.class);
    private final ContactRepository contactRepository;
    private final ObjectMapper objectMapper;

    public contactJsonDataLoader(ContactRepository contactRepository, ObjectMapper objectMapper){
        this.contactRepository=contactRepository;
        this.objectMapper=objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
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
