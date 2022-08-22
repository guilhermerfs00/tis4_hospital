package br.com.pucminas.hospital.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "person")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", nullable = false)
    private Long idPerson;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String address;
}