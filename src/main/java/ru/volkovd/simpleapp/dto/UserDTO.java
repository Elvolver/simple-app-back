package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.volkovd.simpleapp.models.User;

import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public void setSubscriber(User user) {
        this.isSubscriber = user.getSubscriptions().stream().map(u -> u.getId()).collect(Collectors.toList()).contains(this.id);
    }

    public boolean isSubscription() {
        return isSubscription;
    }

    public void setSubscription(User user) {


    }

    private boolean isSubscriber;
    private boolean isSubscription;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.isSubscriber = user.getSubscriptions().stream().map(User::getId).count() != 0;
        this.isSubscription = user.getSubscribers().stream().map(User::getId).count() != 0;
    }
}
