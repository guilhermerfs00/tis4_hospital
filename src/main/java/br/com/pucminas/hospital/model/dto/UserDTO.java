package br.com.pucminas.hospital.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long idUser;

    @NonNull
    @NotEmpty(message = "Usuário não pode ser nulo")
    private String userName;

    @NonNull
    @NotEmpty(message = "Nome não pode ser nulo")
    private String fullName;

    @NonNull
    @NotEmpty(message = "Senha não pode ser nula")
    private String password;

    private List<PermissionDTO> permission;
}