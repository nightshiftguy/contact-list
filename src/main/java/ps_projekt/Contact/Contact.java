package ps_projekt.Contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import ps_projekt.User.User;

@Entity
@Table(name = "Contact" )
@PreAuthorize("hasRole('USER')")
@Getter
@Setter
public class Contact{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "first_name", length = 180)
        @NotEmpty
        private String firstName;
        @Column(name = "last_name", length = 180)
        @NotEmpty
        private String lastName;
        @Column(name = "phone_number", length = 180)
        private String phoneNumber;
        @Email
        @Column(name = "email", length = 180)
        private String email;
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
}
