package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.model.dto.AccountCredentialsDTO;
import br.com.pucminas.hospital.model.dto.TokenDTO;
import br.com.pucminas.hospital.repository.UserRepository;
import br.com.pucminas.hospital.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO) {
        try {
            var username = accountCredentialsDTO.getUsername();
            var password = accountCredentialsDTO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            if (Objects.isNull(user)) throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");

            return tokenProvider.createAccessToken(username, user.getRoles());

        } catch (Exception e) {
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        }
    }

    public TokenDTO refreshToken(String username, String refreshToken) {
        try {
            var user = repository.findByUsername(username);

            if (Objects.isNull(user)) throw new UsernameNotFoundException("Username " + username + " not found!");

            return tokenProvider.refreshToken(refreshToken);

        } catch (Exception e) {
            throw new BadCredentialsException("Não foi possivel gerar o token: " + e.getMessage());
        }
    }
}
