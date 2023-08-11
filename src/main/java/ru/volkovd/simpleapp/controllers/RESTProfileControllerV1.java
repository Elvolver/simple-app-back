package ru.volkovd.simpleapp.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.simpleapp.dto.EditUserProfileRequestDTO;
import ru.volkovd.simpleapp.dto.ProfileResponseDTO;
import ru.volkovd.simpleapp.dto.UserResponseDTO;
import ru.volkovd.simpleapp.models.Avatar;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.AvatarServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/profile")
@Slf4j
@Data
public class RESTProfileControllerV1 {

    private final UserDetailsServiceImpl userService;
    private final AvatarServiceImpl avatarService;

    @Transactional
    @GetMapping()
    public ProfileResponseDTO get(HttpServletRequest request) {
        return new ProfileResponseDTO(userService.getUser(request));
    }

    @PostMapping()
    public UserResponseDTO edit(@RequestBody EditUserProfileRequestDTO profileDTO, HttpServletRequest request) {
        User user = userService.getUser(request);
        user.getProfile().setFirstName(profileDTO.getFirstname());
        user.getProfile().setLastName(profileDTO.getLastname());
        userService.save(user);
        return new UserResponseDTO(userService.getUser(request));
    }

    @PostMapping("/subscribe")
    public UserResponseDTO edit(@RequestBody Long channelId, HttpServletRequest request) {
        User user = userService.getUser(request);
        User channel = userService.getUser(channelId);
        user.getSubscriptions().add(channel);
        userService.save(user);
        return new UserResponseDTO(userService.getUser(request));
    }

    @Transactional
    @PostMapping("/avatar")
    public void edit(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        User user = userService.getUser(request);
        Avatar avatar = avatarService.getFile(user.getId());
        if (avatar == null) {
            avatar = new Avatar(file);
            avatar.setId(user.getId());
        }
        avatar.setData(file);
        //user.setAvatar(avatar);
        avatarService.save(avatar);
        userService.save(user);
    }
}
