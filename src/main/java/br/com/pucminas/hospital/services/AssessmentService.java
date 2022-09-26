package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.AssessmentMapper;
import br.com.pucminas.hospital.mapper.PatientMapper;
import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static br.com.pucminas.hospital.model.enums.AssessmentNumberEnum.*;

@Service
@Slf4j
public class AssessmentService {

    @Autowired
    AssessmentRepository repository;

    @Autowired
    PatientService patientService;

    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO) {
        var patientDTO = patientService.findPatientByRegister(assessmentDTO.getPatientRegister());

        var assessment = AssessmentMapper.INSTANCE.dtoToEntity(assessmentDTO);

        assessment.setCallDay(LocalDate.now());
        assessment.setAssessmentNumberEnum(discoverAssessmentNumber(assessmentDTO.getPatientRegister()));
        assessment.setPatient(PatientMapper.INSTANCE.dtoToEntity(patientDTO));

        return AssessmentMapper.INSTANCE.entityToDto(repository.save(assessment));
    }

    public List<AssessmentDTO> findAssessmentByRegister(String patientRegister) {
        var assessment = repository.findAssessmentByRegister(patientRegister)
                .orElseThrow(() -> new ResourceNotFoundException());

        return AssessmentMapper.INSTANCE.entityToDto(assessment);
    }

    public String discoverAssessmentNumber(String patientRegister) {
        switch ((int) repository.findAssessmentByRegister(patientRegister).get().stream().count()) {
            case 0:
                return PRIMEIRA.getValue();
            case 1:
                return SEGUNDA.getValue();
            case 2:
                return TERCEIRA.getValue();
            default:
                throw new IllegalArgumentException("Erro ao buscar o numero da avaliação");
        }
    }
}
