package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.mapper.AssessmentMapper;
import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.model.entity.Assessment;
import br.com.pucminas.hospital.model.entity.Patient;
import br.com.pucminas.hospital.model.enums.AssessmentNumberEnum;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssessmentService {

    @Autowired
    AssessmentRepository repository;

    private static final Integer SECOND_ASSESSMENT_TRY = 30;
    private static final Integer THIRD_ASSESSMENT_TRY = 60;

    public void createPatientAssessment(Patient patient) {

        List<Assessment> assessments = new ArrayList<>();

        Assessment firstAssessment = new Assessment();
        firstAssessment.setCallDay(LocalDate.now());
        firstAssessment.setPatient(patient);
        firstAssessment.setIsContactDone(false);
        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.PRIMEIRA.getValue());
        firstAssessment.setSymptomsDetail("");


        Assessment secondAssessment = new Assessment();
        secondAssessment.setCallDay(LocalDate.now().plusDays(SECOND_ASSESSMENT_TRY));
        secondAssessment.setPatient(patient);
        secondAssessment.setIsContactDone(false);
        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.SEGUNDA.getValue());
        secondAssessment.setSymptomsDetail("");

        Assessment thirdAssessment = new Assessment();
        thirdAssessment.setCallDay(LocalDate.now().plusDays(THIRD_ASSESSMENT_TRY));
        thirdAssessment.setPatient(patient);
        thirdAssessment.setIsContactDone(false);
        firstAssessment.setAssessmentNumberEnum(AssessmentNumberEnum.TERCEIRA.getValue());
        thirdAssessment.setSymptomsDetail("");

        assessments.add(firstAssessment);
        assessments.add(secondAssessment);
        assessments.add(thirdAssessment);

        repository.saveAll(assessments);
    }

    public List<AssessmentDTO> findAssessmentByRegister(String patientRegister) {
        var assessment = repository.findAssessmentByRegister(patientRegister).get();

        return AssessmentMapper.INSTANCE.entityToDto(assessment);
    }

}