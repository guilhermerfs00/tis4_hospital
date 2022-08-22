package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.mapper.PersonMapper;
import br.com.pucminas.hospital.model.dto.PersonDTO;
import br.com.pucminas.hospital.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pagePerson = repository.findAll(pageable);

        return pagePerson.getContent().stream().map(PersonMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }
}
