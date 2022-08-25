package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.EditUserProfileRequestDTO;
import ru.volkovd.simpleapp.dto.UserResponseDTO;

public interface ProfileService {
    UserResponseDTO edit(EditUserProfileRequestDTO dto);
}
