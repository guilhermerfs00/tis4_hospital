package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    PasswordEncoder passwordEncoder;


    public UserService(UserRepository repository) {
        this.passwordEncoder = new BCryptPasswordEncoder();
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

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        var user = repository.save(UserMapper.INSTANCE.dtoToEntity(userDTO));

        return UserMapper.INSTANCE.entityToDto(user);
    }

    private void validateUser(String username) {
        var user = repository.findByUsername(username);

        if (!Objects.isNull(user)) throw new BusinesException("Usuário já cadastrado no sistema");
    }
}