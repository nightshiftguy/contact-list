package contact_list.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import contact_list.user.User;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIdAndUser(Long id, User currentUser);
}
