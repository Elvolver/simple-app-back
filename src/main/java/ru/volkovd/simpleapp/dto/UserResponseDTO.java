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
    private byte[] avatar;


    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getUserProfile().getFirstName();
        this.lastname = user.getUserProfile().getLastName();
        this.avatar = user.getAvatar().getData();
    }
}
