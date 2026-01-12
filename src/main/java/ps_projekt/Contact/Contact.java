package ps_projekt.Contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Contact" )
@Getter
@Setter
public class Contact{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
        @Column(name = "first_name", length = 180)
        @NotEmpty
        String firstName;
        @Column(name = "last_name", length = 180)
        @NotEmpty
        String lastName;
        @Column(name = "phone_number", length = 180)
        String phoneNumber;
        @Email
        @Column(name = "email", length = 180)
        String email;
}
