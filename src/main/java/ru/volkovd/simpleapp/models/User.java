package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.dto.UserDTO;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserProfile userProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Avatar avatar;

    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ru.volkovd.simpleapp.models.Role role;
    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ru.volkovd.simpleapp.models.Status status;

    public User(RegistrationRequestDTO registrationRequestDTO) {
        this.email = registrationRequestDTO.getEmail();
        this.password = registrationRequestDTO.getPassword();
        this.role = Role.USER;
        this.status = Status.ACTIVE;
    }

    public void addUserProfile(RegistrationRequestDTO request) {
        this.userProfile = new UserProfile(this.id, request.getFirstname(), request.getLastname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
