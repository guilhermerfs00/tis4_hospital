package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.UserMapper;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.User;
import br.com.pucminas.hospital.repository.PermissionRepository;
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

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Autowired
    PermissionRepository permissionRepository;

    PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new Pbkdf2PasswordEncoder();
    }

    public UserDTO createUser(UserDTO userDTO) {

        validateUserAlreadyRegistered(userDTO.getUserName());

        userDTO.getPermission().stream().forEach(p -> permissionRepository.findByDescription(p.getDescription())
                .orElseThrow(() -> new ResourceNotFoundException("Permissão de usuário não encontrada")));

        var user = userSecurityConfig(userDTO);

        user.setUserName(userDTO.getUserName());

        var userResult = UserMapper.INSTANCE.entityToDto(repository.save(user));

        userResult.setUserName(userDTO.getUserName());

        return userResult;
    }

    @Transactional
    public void changePassword(String username, String password) {
        loadUserByUsername(username);
        repository.changePasswordByUsername(username, passwordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username " + username + " não encontrado!"));
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

    public void inactivateUser(String username) {
        var user = findUnregisteredUserByUsername(username);
        user.setEnabled(false);
        repository.save(user);
    }

    private User findUnregisteredUserByUsername(String username) {
        var user = repository.findByUsername(username);

        if (user.isPresent()) {
            throw new BusinesException("Usuário já cadastrado no sistema", HttpStatus.BAD_REQUEST);
        }

        return user.get();
    }

    private void validateUserAlreadyRegistered(String username) {
        var user = repository.findByUsername(username);

        if (user.isPresent()) {
            throw new BusinesException("Usuário já cadastrado no sistema", HttpStatus.BAD_REQUEST);
        }
    }
}