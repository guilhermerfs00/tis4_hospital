package br.com.pucminas.hospital.security;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class AccountCredentials implements Serializable {
    private String username;
    private String password;
}
