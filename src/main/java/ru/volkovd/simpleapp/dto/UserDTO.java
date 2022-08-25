package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.volkovd.simpleapp.models.User;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank
    @Length(min=1)
    private String email;
    @NotBlank
    @Length(min=1)
    private String password;
    private String firstname;
    private String lastname;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstname = user.getUserProfile().getFirstName();
        this.lastname = user.getUserProfile().getLastName();
    }
}
