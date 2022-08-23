package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.User;
import br.com.pucminas.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
    }

    public UserDTO createUser(UserDTO userDTO) {
        validateUser(userDTO.getUserName());
        var user = userConfig(userDTO);

        return UserMapper.INSTANCE.entityToDto(repository.save(user));
    }


    private void validateUser(String username) {
        var user = repository.findByUsername(username);
        if (!Objects.isNull(user)) throw new BusinesException("Usuário já cadastrado no sistema");
    }

    private User userConfig(UserDTO userDTO) {
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