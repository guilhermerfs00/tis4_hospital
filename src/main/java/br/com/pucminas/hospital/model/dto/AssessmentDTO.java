package br.com.pucminas.hospital.model.dto;

import br.com.pucminas.hospital.model.enums.AssesmentCancelReasonEnum;
import br.com.pucminas.hospital.model.enums.AssesmentStatusEnum;
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

    private Long idAssessment;

    private LocalDate callDay;

    private Boolean isContactDone;

    private String symptomsDetail;

    private String patientRegister;

    private String assessmentNumberEnum;

    private AssesmentStatusEnum status;

    private AssesmentCancelReasonEnum cancelReason;

    private Boolean isPatientDead;
}