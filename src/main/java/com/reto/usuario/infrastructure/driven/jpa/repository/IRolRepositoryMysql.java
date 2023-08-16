package com.reto.usuario.infrastructure.driven.jpa.repository;

import com.reto.usuario.infrastructure.driven.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepositoryMysql extends JpaRepository<RolEntity, Long> {

    RolEntity findByName(String name);
}
