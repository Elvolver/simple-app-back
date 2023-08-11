package ru.volkovd.simpleapp.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volkovd.simpleapp.dto.RegistrationRequestDTO;
import ru.volkovd.simpleapp.dto.UserDTO;
import ru.volkovd.simpleapp.dto.UserResponse;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.models.UserProfile;
import ru.volkovd.simpleapp.repository.UserProfileRepository;
import ru.volkovd.simpleapp.repository.UserRepository;
import ru.volkovd.simpleapp.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }

    public User getUserByUserName(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

    public User getUser(HttpServletRequest request) throws UsernameNotFoundException {
        return getUserByUserName(request.getUserPrincipal().getName());
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    public User save(RegistrationRequestDTO registrationRequestDTO) {
        User user = new User(registrationRequestDTO);
        user = userRepository.save(user);
        UserProfile profile = new UserProfile(user.getId(), registrationRequestDTO.getFirstname(), registrationRequestDTO.getLastname());
        userProfileRepository.save(profile);
        user.setProfile(profile);
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public UserResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir, User user) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> usersPage = userRepository.findAll(pageable);

        List<User> listOfUsers = usersPage.getContent();

        List<UserDTO> users = listOfUsers.stream().map(UserDTO::new).collect(Collectors.toList());

        users = users.stream().map(userDTO -> {
            userDTO.setSubscriber(user);
            return userDTO;
        }).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(users);
        userResponse.setPageNo(usersPage.getNumber());
        userResponse.setPageSize(usersPage.getSize());
        userResponse.setTotalElements(usersPage.getTotalElements());
        userResponse.setTotalPages(usersPage.getTotalPages());
        userResponse.setLast(usersPage.isLast());


        return userResponse;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


}
