package ps_projekt.Contact;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactBadRequestException extends RuntimeException{
    public ContactBadRequestException(){
        super("Bad request for Contact");
    }
}

