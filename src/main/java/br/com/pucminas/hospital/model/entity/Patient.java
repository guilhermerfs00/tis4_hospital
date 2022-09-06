package br.com.pucminas.hospital.model.entity;

import br.com.pucminas.hospital.model.enums.SexEnum;
import br.com.pucminas.hospital.model.enums.SurgeryPatientEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "patient")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long idPatient;

    @Column(name = "register", nullable = false, unique = true)
    private String register;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "sex", nullable = false, length = 80)
    private SexEnum sex;

    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "surgery_patient")
    private SurgeryPatientEnum surgeryPatientEnum;

    @Column(name = "surgery_date", nullable = false)
    private LocalDate surgeryDate;

    @Column(name = "is_nhsn_surgery", nullable = false)
    private Boolean isNhsnSurgery;

}