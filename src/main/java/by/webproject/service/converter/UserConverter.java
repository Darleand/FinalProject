package by.webproject.service.converter;

import by.webproject.dto.UserDTO;
import by.webproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<User, UserDTO>{
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    @Override
    public List<User> toEntity(List<UserDTO> list) {
        return list.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    @Override
    public List<UserDTO> toDTO(List<User> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
