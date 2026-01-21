package ps_projekt.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import ps_projekt.User.User;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIdAndUser(Long id, User currentUser);
}
