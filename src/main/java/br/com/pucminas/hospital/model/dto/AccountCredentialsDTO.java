package br.com.pucminas.hospital.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCredentialsDTO {

    private String username;
    private String password;
}
