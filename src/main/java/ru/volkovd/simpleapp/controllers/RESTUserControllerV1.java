package ru.volkovd.simpleapp.controllers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.dto.UserDTO;
import ru.volkovd.simpleapp.dto.UserResponse;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.AvatarServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@Slf4j
@Data
public class RESTUserControllerV1 {

    private final UserDetailsServiceImpl userService;
    private final AvatarServiceImpl avatarService;

    @GetMapping
    @Transactional
    public UserResponse getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = "${pagination.DEFAULT_PAGE_NUMBER}", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "${pagination.DEFAULT_PAGE_SIZE}", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "${pagination.DEFAULT_SORT_BY}", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "${pagination.DEFAULT_SORT_DIRECTION}", required = false) String sortDir,
            HttpServletRequest request

    ) {
        User user = userService.getUser(request);
        return userService.getAll(pageNo, pageSize, sortBy, sortDir, user);
    }

    @GetMapping("/user/{user}")
    @Transactional
    public UserDTO getOneUser(@PathVariable User user,
                              HttpServletRequest request

    ) {
        return new UserDTO(userService.getUser(user.getId()));
    }
}
