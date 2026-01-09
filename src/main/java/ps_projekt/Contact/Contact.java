package ps_projekt.Contact;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record Contact(
        Integer id,
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName,
        String phoneNumber,
        @Email
        String email
) {
    public Contact{
        if(phoneNumber.length() != 9){
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
    }
}
