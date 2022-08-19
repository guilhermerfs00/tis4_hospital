package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.data.dto.PersonDTO;
import br.com.pucminas.hospital.data.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO entityToDto(Person entity);

    Person dtoToEntity(PersonDTO dto);
}
