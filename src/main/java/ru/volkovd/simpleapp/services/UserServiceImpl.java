package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.dto.UserDTO;
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
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
