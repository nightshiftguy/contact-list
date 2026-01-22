package ps_projekt.Contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends ResponseStatusException {
    public ContactNotFoundException(){
        super(HttpStatus.NOT_FOUND,"Contact not found");
    }
}
