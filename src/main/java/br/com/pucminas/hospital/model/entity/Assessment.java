package br.com.pucminas.hospital.model.entity;

import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "assessment")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assessment")
    private Long idAssessment;

    @Column(name = "assessment_number")
    private AssessmentNumberEnum assessmentNumberEnum;

    @Column(name = "first_try")
    private LocalDate firstTry;

    @Column(name = "second_try")
    private LocalDate secondTry;

    @Column(name = "final_try")
    private LocalDate finalTry;

    @Column(name = "is_contact_done")
    private Boolean isContactDone;

    @Column(name = "symptoms_detail")
    private String symptomsDetail;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}