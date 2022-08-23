package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.AccountCredentialsDTO;
import br.com.pucminas.hospital.model.dto.TokenDTO;
import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.services.AuthService;
import br.com.pucminas.hospital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        var response = authService.signin(accountCredentialsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        var response = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String authorization) {
        var response = authService.refreshToken(username, authorization);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
