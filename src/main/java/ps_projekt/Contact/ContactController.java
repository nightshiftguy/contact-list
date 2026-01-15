package ps_projekt.Contact;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ps_projekt.Email.EmailService;
import ps_projekt.User.User;
import ps_projekt.User.UserRepository;
import ps_projekt.utils.HtmlMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    private Optional<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        return userRepository.findByEmail(currentUserEmail);
    }

    @GetMapping("")
    List<Contact> findUserContacts(){
        return getCurrentUser().get().getContacts();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/send-email")
    void sendEmailWithContacts(){
        List<Contact> contacts = getCurrentUser().get().getContacts();
        String htmlContent = HtmlMapper.mapContactListToHtmlTable(contacts);
        EmailService.SendEmailSMTP(
                getCurrentUser().get().getEmail(),
                "Your Contacts - Contact List From PS Projekt",
                htmlContent
        );
    }
    @GetMapping("/{id}")
    Contact findById(@PathVariable Long id) throws RuntimeException {
        List<Contact> contacts =getCurrentUser().get().getContacts();
        Optional<Contact> contact = contacts.stream().filter(c -> c.getId().equals(id)).findFirst();
        if(contact.isEmpty()){
            throw new ContactNotFoundException();
        }
        return contact.get();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody Contact contact){
        if(contact.getUser()!=null){
            throw new ContactBadRequestException();
        }
        contact.setUser(getCurrentUser().get());
        contactRepository.save(contact);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Contact contact, @PathVariable Integer id){
        if(!getCurrentUser().get().getContacts().contains(contact)){
            throw new ContactNotFoundException();
        }
        contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        Contact contact = contactRepository.findById(id).get();
        if(!getCurrentUser().get().getContacts().contains(contact)){
            throw new ContactNotFoundException();
        }
        contactRepository.delete(contact);
    }
}
