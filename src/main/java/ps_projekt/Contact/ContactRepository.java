package ps_projekt.Contact;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();
    List<Contact> findAll(){
        return contacts;
    }

    Optional<Contact> findById(Integer id){
        return contacts.stream().filter(contact -> contact.id() == id).findFirst();
    }

    void create(Contact contact){
        contacts.add(contact);
    }

    void update(Contact contact, Integer id){
        Optional<Contact> existingContact = findById(id);
        if(existingContact.isPresent()){
            contacts.set(contacts.indexOf(existingContact.get()), contact);
        }
    }

    void delete(Integer id){
        contacts.removeIf(contact -> contact.id().equals(id));
    }

    @PostConstruct
    private void init(){
        contacts.add(new Contact(1,"John","Kowalski","111222333", "email@x"));
        contacts.add(new Contact(2,"Michael","Kowalski","123123123", "email@y"));
        contacts.add(new Contact(3,"Emil","Nowak","000111000", "email@z"));
    }
}
