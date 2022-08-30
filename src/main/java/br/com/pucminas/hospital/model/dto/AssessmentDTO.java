package br.com.pucminas.hospital.model.dto;

import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDTO {

    @JsonIgnore
    private Integer idAssessment;

    @NotEmpty(message = "Numero da avaliação não pode ser nulo")
    private AssessmentNumberEnum assessmentNumberEnum;

    private LocalDate firstTry;

    private LocalDate secondTry;

    private LocalDate finalTry;

    @NotEmpty(message = "Contato realizado não pode ser nulo")
    private Boolean isContactDone;

    private String symptomsDetail;

    @NotEmpty(message = "Registro do paciente não pode ser nulo")
    private String patientRegister;

}