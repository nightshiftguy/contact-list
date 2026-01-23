package ps_projekt.Auth;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ps_projekt.Contact.ContactRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({ContactRequest.First.class, ContactRequest.Second.class, RegisterRequest.class})
public class RegisterRequest {
    public interface First {}
    public interface Second {}
    @NotBlank(groups = ContactRequest.First.class)
    @Email(groups = ContactRequest.Second.class)
    private String email;
    @NotBlank(groups = ContactRequest.First.class)
    @Size(min = 8,message = "must be at least 8 characters long", groups = ContactRequest.Second.class)
    private String password;
}
