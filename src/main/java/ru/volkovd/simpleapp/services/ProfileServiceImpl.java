package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.EditUserProfileRequestDTO;
import ru.volkovd.simpleapp.dto.UserResponseDTO;
import ru.volkovd.simpleapp.models.User;

public class ProfileServiceImpl implements ProfileService {

    @Override
    public UserResponseDTO edit(EditUserProfileRequestDTO dto) {
        return new UserResponseDTO(new User());
    }
}
