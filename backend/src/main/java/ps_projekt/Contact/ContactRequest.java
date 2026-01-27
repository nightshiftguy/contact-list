package ps_projekt.Contact;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({ContactRequest.First.class, ContactRequest.Second.class, ContactRequest.class})
public class ContactRequest {

    public interface First {}
    public interface Second {}

    @NotBlank(groups = First.class)
    @Length(max = 180, message = "First name must be at most 180 characters", groups = First.class)
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Only letters are allowed", groups = Second.class)
    private String firstName;

    @NotBlank(groups = First.class)
    @Length(max = 180, message = "First name must be at most 180 characters", groups = First.class)
    @Pattern(regexp = "^[\\p{L} -]+$", message = "Only letters are allowed", groups = Second.class)
    private String lastName;

    @NotBlank(groups = First.class)
    @Size(min = 9, max = 9, message = "Phone number must be exactly 9 digits", groups = Second.class)
    @Pattern(regexp = "^[0-9]+$", message = "Only digits are allowed", groups = Second.class)
    private String phoneNumber;

    @NotBlank(groups = First.class)
    @Email(groups = Second.class)
    private String email;
}

