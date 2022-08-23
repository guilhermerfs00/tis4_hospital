package br.com.pucminas.hospital.repository;

import br.com.pucminas.hospital.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByDescription(String description);

    @Transactional
    void deleteByDescription(String description);
}