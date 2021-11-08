package by.webproject.service;

import by.webproject.dto.UserDTO;

import java.util.List;

public interface UserServiceInter {

    UserDTO findUserById(long id);

    List<UserDTO> findAll();

    UserDTO saveUser(UserDTO user);

    void deleteUser(long userId);
}
