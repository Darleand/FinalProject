package by.webproject.service;

import by.webproject.dto.UserDTO;
import by.webproject.entity.Role;
import by.webproject.entity.User;
import by.webproject.repository.RoleRepository;
import by.webproject.repository.UserRepository;
import by.webproject.service.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Component
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder bcpe;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, UserConverter userConverter, BCryptPasswordEncoder bcpe) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.bcpe = bcpe;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getByLogin(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getByLogin(String login) {
        return userRepository.findAll().stream()
                .filter(user -> login.equals(user.getUsername())).findFirst().orElse(null);
    }

    public UserDTO findUserById(long id) {
        return userConverter
                .toDTO(userRepository.findById(id).orElse(new User()));
    }

    public List<UserDTO> allUsers() {
        return userConverter.toDTO(userRepository.findAll());
    }

    public boolean saveUser(UserDTO user) {
        User userDB = userRepository.findByUsername(user.getUsername());

        if (userDB != null) {
            return false;
        }

        userDB.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userDB.setPassword(bcpe.encode(user.getPassword()));
        userRepository.save(userDB);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
