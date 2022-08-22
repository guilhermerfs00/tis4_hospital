package br.com.pucminas.hospital.controller;

import br.com.pucminas.hospital.model.dto.AccountCredentialsDTO;
import br.com.pucminas.hospital.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        if (authService.checkIfParamsIsNull(accountCredentialsDTO)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação inválida!");
        }
        var token = authService.signin(accountCredentialsDTO);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Solicitação inválida!");
        } else {
            return token;
        }
    }
}
