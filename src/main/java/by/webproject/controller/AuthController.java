package by.webproject.controller;

import by.webproject.dto.UserDTO;
import by.webproject.service.UserService;
import by.webproject.web.NameOfUserEngagedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/web")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public UserDTO auth(Principal principal) {
        LOGGER.debug("User {} logged in", principal.getName());
        UserDTO user = new UserDTO();
        user.setUsername(principal.getName());
        return user;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.saveUser(userDTO);
        return ResponseEntity.ok(user);
    }

}