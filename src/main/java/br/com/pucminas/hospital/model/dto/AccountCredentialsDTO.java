package br.com.pucminas.hospital.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCredentialsDTO {

    @NonNull
    private String username;
    @NonNull
    private String password;
}
