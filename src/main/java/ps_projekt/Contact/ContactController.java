package ps_projekt.Contact;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/contacts")
public class ContactController {
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    @GetMapping("")
    List<Contact> findAll(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    Contact findById(@PathVariable Integer id){
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isEmpty()){
            throw new ContactNotFoundException();
        }
        return contact.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Contact contact){
        contactRepository.create(contact);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Contact contact, @PathVariable Integer id){
        contactRepository.update(contact, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        contactRepository.delete(id);
    }
}
