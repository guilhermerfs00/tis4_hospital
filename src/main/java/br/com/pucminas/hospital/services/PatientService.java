package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.PatientMapper;
import br.com.pucminas.hospital.model.dto.PatientDTO;
import br.com.pucminas.hospital.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
public class PatientService {

    @Autowired
    PatientRepository repository;

    public List<PatientDTO> findAll(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pagePatient = repository.findAll(pageable);

        return pagePatient.getContent().stream().map(PatientMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    public PatientDTO findByRegister(String register) {
        var optPatient = repository.findByRegister(register);

        isPassientExistet(optPatient);

        return PatientMapper.INSTANCE.entityToDto(optPatient.get());
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        var patient = repository.save(PatientMapper.INSTANCE.dtoToEntity(patientDTO));
        return PatientMapper.INSTANCE.entityToDto(patient);
    }

    public PatientDTO updatePatient(String register, PatientDTO patientDTO) {
        var optionalPatient = repository.findByRegister(register);

        isPassientExistet(optionalPatient);


        patientDTO.setIdPatient(optionalPatient.get().getIdPatient());

        var patient = repository.save(PatientMapper.INSTANCE.dtoToEntity(patientDTO));

        return PatientMapper.INSTANCE.entityToDto(patient);
    }

    public void isPassientExistet(Optional optPatient) {
        if (isNull(optPatient) || !optPatient.isPresent()) {
            throw new BusinesException("Paciente não encontrado", NOT_FOUND);
        }
    }
}
