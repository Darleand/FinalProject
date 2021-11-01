package by.webproject.controller;

import by.webproject.dto.UserDTO;
import by.webproject.entity.User;
import by.webproject.service.UserService;
import by.webproject.service.converter.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/web")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO auth(Principal principal) {
        LOGGER.debug("User {} logged in", principal.getName());
        UserDTO user = new UserDTO();
        user.setUsername(principal.getName());
        return user;
    }

    @PostMapping("/register")
    public Boolean register(UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}