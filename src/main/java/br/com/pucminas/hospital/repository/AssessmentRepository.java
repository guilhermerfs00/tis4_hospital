package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Assessment;
import br.com.pucminas.hospital.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    @Query("SELECT a FROM Assessment a WHERE a.callDay = CURRENT_DATE")
    List<Assessment> findAllDailyAssessment();

    @Query("SELECT a FROM Assessment a WHERE a.patient.register = :register")
    Optional<List<Assessment>> findAssessmentByRegister(@Param("register") String register);

    @Query("SELECT a FROM Assessment a WHERE a.callDay BETWEEN :startDate AND :finalDate")
    List<Assessment> getStatisticByAssessment(@Param("startDate") LocalDate startDate,
                                              @Param("finalDate") LocalDate finalDate);

    @Query("SELECT a FROM Assessment a WHERE a.patient.idPatient IN :idsPatient ")
    List<Assessment> getAssessmentsByIdPatient(@Param("idsPatient") List<Long> idsPatient);

}