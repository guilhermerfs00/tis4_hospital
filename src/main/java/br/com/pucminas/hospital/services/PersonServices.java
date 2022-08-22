package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.data.dto.PersonDTO;
import br.com.pucminas.hospital.data.entity.Person;
import br.com.pucminas.hospital.exceptions.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PersonMapper;
import br.com.pucminas.hospital.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonDTO create(Person person) {
        return null;
    }

    public Page<PersonDTO> findPersonByName(String firstName, Pageable pageable) {
        return null;
    }

    public List<PersonDTO> findAll(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pagePerson = repository.findAll(pageable);

        return pagePerson.getContent().stream().map(PersonMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) {

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado com esse ID"));
        return null;
    }

    public PersonDTO update(Person person) {
        return null;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
