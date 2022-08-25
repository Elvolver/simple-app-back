package ru.volkovd.simpleapp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.repository.UserRepository;
import ru.volkovd.simpleapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }

    @Transactional
    public User getUserByUserName(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

    @Transactional
    public User getUser(HttpServletRequest request) throws UsernameNotFoundException {
        return getUserByUserName(request.getUserPrincipal().getName());
    }

    public User save(RegistrationRequestDTO registrationRequestDTO) {
        return userRepository.save(new User(registrationRequestDTO));
    }

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
