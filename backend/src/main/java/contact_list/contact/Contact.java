package contact_list.contact;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import contact_list.security.SensitiveFieldEncryptor;
import contact_list.user.User;

@Entity
@Table(name = "Contact" )
@Getter
@Setter
public class Contact{
        @Id
        @GeneratedValue
        private Long id;
        @NotEmpty
        @Column(name = "first_name", length = 180)
        private String firstName;
        @NotEmpty
        @Column(name = "last_name", length = 180)
        private String lastName;
        @NotEmpty
        @Column(name = "phone_number", length = 180)
        @Convert(converter = SensitiveFieldEncryptor.class)
        private String phoneNumber;
        @Email
        @NotEmpty
        @Column(name = "email", length = 180)
        @Convert(converter = SensitiveFieldEncryptor.class)
        private String email;
        @ManyToOne
        @JoinColumn(name = "user_id")
        @JsonBackReference
        private User user;
}
