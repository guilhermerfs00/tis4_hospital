package br.com.pucminas.hospital.services;

import br.com.pucminas.hospital.exceptions.BusinesException;
import br.com.pucminas.hospital.mapper.PermissionMapper;
import br.com.pucminas.hospital.model.dto.PermissionDTO;
import br.com.pucminas.hospital.model.entity.Permission;
import br.com.pucminas.hospital.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository repository;

    public PermissionDTO create(String description) {
        try {
            return PermissionMapper.INSTANCE.entityToDto(repository.save(Permission.builder().description(description).build()));
        } catch (Exception e) {
            throw new BusinesException("Permissão: " + description + " já cadastrada no sistema !");
        }
    }

    public List<PermissionDTO> findAll() {
        var permissionList = repository.findAll();
        return permissionList.stream().map(PermissionMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    public void deleteByDescription(String description) {

        var permission = repository.findByDescription(description);
        if (Objects.isNull(permission)) {
            throw new BusinesException("Permissão não encontrada");
        }
        repository.deleteByDescription(description);
    }
}
