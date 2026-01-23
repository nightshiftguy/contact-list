package ps_projekt.Contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ps_projekt.Email.EmailService;
import ps_projekt.User.User;
import ps_projekt.User.UserRepository;
import ps_projekt.utils.HtmlMapper;

import java.util.List;

@RestController
@RequestMapping("api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        return userRepository.findByEmail(currentUserEmail)
                .orElseThrow(()-> new RuntimeException("User not found")
                );
    }

    @GetMapping("")
    List<Contact> findUserContacts(){
        return getCurrentUser().getContacts();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/send-email")
    void sendEmailWithContacts(){
        List<Contact> contacts = getCurrentUser().getContacts();
        String htmlContent = HtmlMapper.mapContactListToHtmlTable(contacts);
        EmailService.SendEmailSMTP(
                getCurrentUser().getEmail(),
                "Your Contacts - Contact List From PS Projekt",
                htmlContent
        );
    }
    @GetMapping("/{id}")
    public Contact findById(@PathVariable Long id) {
        User user = getCurrentUser();
        return contactRepository.findByIdAndUser(id, user)
                .orElseThrow(ContactNotFoundException::new);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody ContactRequest newContact){
        Contact contact = new Contact();
        contact.setFirstName(newContact.getFirstName());
        contact.setLastName(newContact.getLastName());
        contact.setEmail(newContact.getEmail());
        contact.setPhoneNumber(newContact.getPhoneNumber());

        contact.setUser(getCurrentUser());
        contactRepository.save(contact);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody ContactRequest newContact, @PathVariable Long id){
        Contact contact = contactRepository
                .findByIdAndUser(id, getCurrentUser())
                .orElseThrow(ContactNotFoundException::new);

        contact.setFirstName(newContact.getFirstName());
        contact.setLastName(newContact.getLastName());
        contact.setEmail(newContact.getEmail());
        contact.setPhoneNumber(newContact.getPhoneNumber());

        contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        Contact contact = contactRepository
                .findByIdAndUser(id, getCurrentUser())
                .orElseThrow(ContactNotFoundException::new);
        contactRepository.delete(contact);
    }
}
