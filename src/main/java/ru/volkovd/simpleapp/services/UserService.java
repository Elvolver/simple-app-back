package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.models.User;

import java.util.List;

public interface UserService {
    User save(RegistrationRequestDTO user);

    //Post save(UserDTO user);
    User save(User user);

    List<User> getAll();
    void delete(User user);
}
