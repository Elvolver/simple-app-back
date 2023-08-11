package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.dto.UserResponse;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(RegistrationRequestDTO registrationRequestDTO) {
        return userRepository.save(new User(registrationRequestDTO));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir, User user) {
        return null;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getById(id);
    }
}
