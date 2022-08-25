package ru.volkovd.simpleapp.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.simpleapp.dto.EditUserProfileRequestDTO;
import ru.volkovd.simpleapp.dto.UserDTO;
import ru.volkovd.simpleapp.dto.UserResponseDTO;
import ru.volkovd.simpleapp.models.Avatar;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.models.UserProfile;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.AvatarServiceImpl;
import ru.volkovd.simpleapp.services.ProfileService;
import ru.volkovd.simpleapp.services.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@Data
public class RESTUserControllerV1 {

    private final UserDetailsServiceImpl userService;
    private final AvatarServiceImpl avatarService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/profile")
    public UserResponseDTO get(HttpServletRequest request) {
        return new UserResponseDTO(userService.getUser(request));
    }

    @PostMapping("/profile")
    public UserResponseDTO edit(@RequestBody EditUserProfileRequestDTO profileDTO, HttpServletRequest request) {
        User user = userService.getUser(request);
        user.getUserProfile().setFirstName(profileDTO.getFirstname());
        user.getUserProfile().setLastName(profileDTO.getLastname());
        userService.save(user);
        return new UserResponseDTO(userService.getUser(request));
    }

    @PostMapping("/profile/avatar")
    @Transactional
    public  void edit(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println(file);
        User user = userService.getUser(request);
        Avatar avatar = avatarService.getFile(user.getId());
        if (avatar == null) {
            avatar = new Avatar(file);
            avatar.setId(user.getId());
        }
        avatar.setData(file);
        user.setAvatar(avatar);
        avatarService.save(avatar);
        userService.save(user);
    }
}
