package ps_projekt.Contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactBadRequestException extends ResponseStatusException {
    public ContactBadRequestException(){
        super(HttpStatus.BAD_REQUEST);
    }
}

