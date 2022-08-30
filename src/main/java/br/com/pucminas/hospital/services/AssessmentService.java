package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.mapper.AssessmentMapper;
import br.com.pucminas.hospital.mapper.PatientMapper;
import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.repository.AssessmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AssessmentService {

    @Autowired
    AssessmentRepository repository;

    @Autowired
    PatientService patientService;

    public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO) {
        var patientDTO = patientService.findByRegister(assessmentDTO.getPatientRegister());

        var assessment = AssessmentMapper.INSTANCE.dtoToEntity(assessmentDTO);
        assessment.setPatient(PatientMapper.INSTANCE.dtoToEntity(patientDTO));

        return AssessmentMapper.INSTANCE.entityToDto(repository.save(assessment));
    }
}
