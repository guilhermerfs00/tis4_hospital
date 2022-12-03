package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByRegister(String register);

    Page<Patient> findByRegisterContaining(String register, Pageable sort);
}