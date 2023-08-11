package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.volkovd.simpleapp.models.User;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProfileResponseDTO {
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
    private Set<User> subscriptions;

    public ProfileResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getProfile().getFirstName();
        this.lastname = user.getProfile().getLastName();
        //this.avatar = user.getAvatar().getData();
        this.subscriptions = user.getSubscriptions();
    }
}
