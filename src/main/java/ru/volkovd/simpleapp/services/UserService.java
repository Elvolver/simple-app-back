package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.dto.UserResponse;
import ru.volkovd.simpleapp.models.User;

import java.util.List;

public interface UserService {
    User save(RegistrationRequestDTO user);

    //Post save(UserDTO user);
    User save(User user);


    UserResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir, User user);

    void delete(User user);

    User getUser(Long id);
}
