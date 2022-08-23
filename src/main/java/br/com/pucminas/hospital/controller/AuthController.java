package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.AccountCredentialsDTO;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.services.AuthService;
import br.com.pucminas.hospital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.pucminas.hospital.util.GernericMessage.SOLICITACAO_INVALIDA;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<String> signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        if (authService.checkIfParamsIsNotNull(accountCredentialsDTO)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SOLICITACAO_INVALIDA);
        }
        var token = authService.signin(accountCredentialsDTO);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SOLICITACAO_INVALIDA);
        } else {
            return token;
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        var response = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity<String> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        if (authService.checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SOLICITACAO_INVALIDA);
        var token = authService.refreshToken(username, refreshToken);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(SOLICITACAO_INVALIDA);
        }
        return token;
    }
}
