package ru.volkovd.simpleapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usr")
@Data
public class User {
    @Id
    String id;
    String name;
    String userpic;
    String email;
    String gender;
    String locale;
    String lastVisit;
}
