package ps_projekt.Contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ps_projekt.User.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin-contacts")
@RequiredArgsConstructor
public class AdminContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @GetMapping("")
    List<Contact> findAll(){
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    Contact findById(@PathVariable Long id){
        Optional<Contact> contact = contactRepository.findById(id);
        if(contact.isEmpty()){
            throw new ContactNotFoundException();
        }
        return contact.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Contact contact){
        contactRepository.save(contact);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Contact contact, @PathVariable Integer id){
        contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        contactRepository.delete(contactRepository.findById(id).get());
    }
}
