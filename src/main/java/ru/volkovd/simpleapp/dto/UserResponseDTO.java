package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.volkovd.simpleapp.models.User;

import javax.validation.constraints.NotBlank;

@Data
public class UserResponseDTO {
    private Long id;
    @NotBlank
    @Length(min=1)
    private String email;
    @NotBlank
    @Length(min=1)
    private String firstname;
    @NotBlank
    @Length(min=1)
    private String lastname;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getProfile().getFirstName();
        this.lastname = user.getProfile().getLastName();
    }
}
