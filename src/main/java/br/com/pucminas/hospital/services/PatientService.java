package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PatientMapper;
import br.com.pucminas.hospital.model.dto.PatientDTO;
import br.com.pucminas.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.pucminas.hospital.model.enums.GenderEnum.M;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    UserTokenService userTokenService;

    public List<PatientDTO> findAllPatientByRegister(int page, int limit, String register) {
        var pageable = PageRequest.of(page, limit);

        var pagePatient = repository.findByRegisterContaining(register, pageable);

        return pagePatient.getContent().stream().map(PatientMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    public PatientDTO findPatientByRegister(String register) {
        var optPatient = repository.findByRegister(register).orElseThrow(() -> new ResourceNotFoundException());

        return PatientMapper.INSTANCE.entityToDto(optPatient);
    }

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO, String authorization) {

        var genderDefaut = M;

        var userName = userTokenService.findUserByToken(authorization).getFullName();
        patientDTO.setCreatedBy(userName);
        patientDTO.setLastModified(LocalDate.now());
        patientDTO.setGenderEnum(genderDefaut.getValue());

        var patient = PatientMapper.INSTANCE.dtoToEntity(patientDTO);

        patient.setGenderEnum(genderDefaut.getValue());

        patient = repository.save(patient);

        assessmentService.createPatientAssessment(patient);

        return PatientMapper.INSTANCE.entityToDto(patient);
    }

    public PatientDTO updatePatient(PatientDTO patientDTO, String authorization) {
        try {
            var patient = repository.findByRegister(patientDTO.getRegister())
                    .orElseThrow(() -> new ResourceNotFoundException());

            patient.setGenderEnum(patientDTO.getGenderEnum());
            patient.setRegister(patientDTO.getRegister());
            patient.setName(patient.getName());
            patient.setLastModified(LocalDate.now());
            patient.setCreatedBy(userTokenService.findUserByToken(authorization).getFullName());
            patient.setSurgeryPatientEnum(patientDTO.getSurgeryPatientEnum());
            patient.setIsNhsnSurgery(patientDTO.getIsNhsnSurgery());
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
            patient.setSurgeryDate(patientDTO.getSurgeryDate());

            patient = repository.save(patient);

            return PatientMapper.INSTANCE.entityToDto(patient);
        } catch (Exception e) {
            throw new BusinesException("Erro ao atualizar usuario: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
