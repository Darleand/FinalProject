package by.webproject.service;

import by.webproject.dto.UserDTO;
import by.webproject.entity.Role;
import by.webproject.entity.User;
import by.webproject.repository.RoleRepository;
import by.webproject.repository.UserRepository;
import by.webproject.service.converter.UserConverter;
import by.webproject.web.CredentialsException;
import by.webproject.web.NameOfUserEngagedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class UserService implements UserDetailsService, UserServiceInter {
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

    public UserDTO findUserById(long id) {
        return userConverter
                .toDTO(userRepository.findById(id).orElse(new User()));
    }

    public UserDTO saveUser(UserDTO user) {
        User userDB = userRepository.findByUsername(user.getUsername());

        if (userDB == null) {
            if (user.getPassword().equals(user.getPasswordConfirm())) {
                userDB = new User();
                userDB.setUsername(user.getUsername());
                userDB.getRoles().add(roleRepository.getRole("ROLE_USER"));
                userDB.setPassword(bcpe.encode(user.getPassword()));
                userDB.setEmail(user.getEmail());
                userRepository.save(userDB);
                UserDTO userDTO = userConverter.toDTO(userDB);
                userDTO.setPassword(null);
                return userDTO;
            }else {
                throw new CredentialsException();
            }
        } else {
            throw new NameOfUserEngagedException();
        }
    }

    public void deleteUser(long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userConverter::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        UserBuilder userBuilder = null;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(s);
            userBuilder.password(user.getPassword());
            userBuilder.authorities(rolesToAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException(String.format("User '%s' not found",s));
        }
        return userBuilder.build();
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Set<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
