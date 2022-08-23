package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.model.dto.UserDTO;
import br.com.pucminas.hospital.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO entityToDto(User entity);

    User dtoToEntity(UserDTO dto);
}
