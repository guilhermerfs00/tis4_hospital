package br.com.pucminas.hospital.mapper;

import br.com.pucminas.hospital.model.dto.AssessmentDTO;
import br.com.pucminas.hospital.model.entity.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssessmentMapper {

    AssessmentMapper INSTANCE = Mappers.getMapper(AssessmentMapper.class);

    AssessmentDTO entityToDto(Assessment entity);

    Assessment dtoToEntity(AssessmentDTO dto);
}
