package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PatientMapper;
import br.com.pucminas.hospital.model.dto.PatientDTO;
import br.com.pucminas.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    PatientRepository repository;

    public List<PatientDTO> findAllPatient(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pagePatient = repository.findAll(pageable);

        return pagePatient.getContent().stream().map(PatientMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    public PatientDTO findPatientByRegister(String register) {
        var optPatient = repository.findByRegister(register).orElseThrow(() -> new ResourceNotFoundException());

        return PatientMapper.INSTANCE.entityToDto(optPatient);
    }

    public PatientDTO createPatient(PatientDTO patientDTO) {
        var patient = repository.save(PatientMapper.INSTANCE.dtoToEntity(patientDTO));
        return PatientMapper.INSTANCE.entityToDto(patient);
    }

    public PatientDTO updatePatient(String register, PatientDTO patientDTO) {
        var optionalPatient = repository.findByRegister(register).orElseThrow(() -> new ResourceNotFoundException());

        patientDTO.setIdPatient(optionalPatient.getIdPatient());

        var patient = repository.save(PatientMapper.INSTANCE.dtoToEntity(patientDTO));

        return PatientMapper.INSTANCE.entityToDto(patient);
    }

//    public PatientDTO patientFilter(MultiValueMap<String, String> requestParams) {
//
//       var iterator = requestParams.entrySet().iterator();
//
//        while(iterator.hasNext()) {
//            var i = iterator.next().getKey();
//            var values = iterator.next().getValue();
//        }
//
//        return null;
//    }
}
