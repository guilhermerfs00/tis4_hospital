package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.User;
import br.com.pucminas.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
        this.repository = repository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        validateUserAlreadyRegistered(userDTO.getUserName());
        var user = userSecurityConfig(userDTO);

        return UserMapper.INSTANCE.entityToDto(repository.save(user));
    }

    @Transactional
    public void changePassword(String username, String password) {
        loadUserByUsername(username);
        repository.changePasswordByUsername(username,  passwordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (!Objects.isNull(user)) {
            return user;
        } else {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
    }

    private void validateUserAlreadyRegistered(String username) {
        var user = repository.findByUsername(username);
        if (!Objects.isNull(user)) throw new BusinesException("Usuário já cadastrado no sistema", HttpStatus.NOT_FOUND);
    }

    private User userSecurityConfig(UserDTO userDTO) {
        var user = UserMapper.INSTANCE.dtoToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        return user;
    }
}