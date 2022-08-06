package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ru.volkovd.simpleapp.models.Role role;
    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private ru.volkovd.simpleapp.models.Status status;


}
