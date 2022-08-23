package br.com.pucminas.hospital.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long idUser;

    @NonNull
    private String userName;

    @NonNull
    private String fullName;

    @NonNull
    private String password;

}