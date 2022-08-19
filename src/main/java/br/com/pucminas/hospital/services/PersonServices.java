package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.data.dto.PersonDTO;
import br.com.pucminas.hospital.data.entity.Person;
import br.com.pucminas.hospital.exception.ResourceNotFoundException;
import br.com.pucminas.hospital.mapper.PersonMapper;
import br.com.pucminas.hospital.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonDTO create(Person person) {
//		var entity = DozerConverter.parseObject(person, Person.class);
//		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
//		return vo;
        return null;
    }

    public Page<PersonDTO> findPersonByName(String firstName, Pageable pageable) {
//        var page = repository.findPersonByName(firstName, pageable);
//        return page.map(this::convertToPersonVO);
        return null;
    }

    public List<PersonDTO> findAll(int page, int limit) {
        var pageable = PageRequest.of(page, limit);
        var pagePerson = repository.findAll(pageable);

        return pagePerson.getContent().stream().map(PersonMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }


    public PersonDTO findById(Long id) {

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
//        return DozerConverter.parseObject(entity, PersonVO.class);
        return null;
    }

    public PersonDTO update(Person person) {
//        var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
//
//        entity.setFirstName(person.getFirstName());
//        entity.setLastName(person.getLastName());
//        entity.setAddress(person.getAddress());
//        entity.setGender(person.getGender());
//
//        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
//        return vo;
        return null;
    }

    @Transactional
    public Person disablePerson(Long id) {
//        repository.disablePersons(id);
//        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
//        return DozerConverter.parseObject(entity, PersonVO.class);
        return null;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }
}
