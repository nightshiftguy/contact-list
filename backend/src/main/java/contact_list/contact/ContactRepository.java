package contact_list.contact;

import contact_list.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
  Optional<Contact> findByIdAndUser(Long id, User currentUser);
}
