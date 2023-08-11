package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Table(name = "user_profile")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter @ToString @RequiredArgsConstructor @EqualsAndHashCode
public class UserProfile {
    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public UserProfile(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
