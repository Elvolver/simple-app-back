package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.context.annotation.Profile;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    @JsonIgnore
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserProfile profile;

    //    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
//    @PrimaryKeyJoinColumn
//    private Avatar avatar;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
    private Set<User> subscribers = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    private Set<User> subscriptions = new HashSet<>();

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private ru.volkovd.simpleapp.models.Role role;

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private ru.volkovd.simpleapp.models.Status status;

    public User(RegistrationRequestDTO registrationRequestDTO) {
        this.email = registrationRequestDTO.getEmail();
        this.password = registrationRequestDTO.getPassword();
        this.role = Role.USER;
        this.status = Status.ACTIVE;
    }
}
