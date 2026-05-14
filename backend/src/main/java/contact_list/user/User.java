package contact_list.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import contact_list.contact.Contact;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
  @Id @GeneratedValue private Long id;
  private String email;
  private String password;
  private Boolean enabled;
  private Boolean locked;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "verification_code")
  private String verificationCode;

  @Column(name = "verification_expiration")
  private LocalDateTime verificationCodeExpiresAt;

  @OneToMany(mappedBy = "user")
  @JsonManagedReference
  @Getter
  private List<Contact> contacts;

  public User(Role role, Boolean enabled, String password, String email) {
    this.role = role;
    this.locked = false;
    this.enabled = enabled;
    this.password = password;
    this.email = email;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
