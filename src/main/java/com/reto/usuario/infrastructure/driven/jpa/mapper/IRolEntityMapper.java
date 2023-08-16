package com.reto.usuario.infrastructure.driven.jpa.mapper;

import com.reto.usuario.domain.model.RolModel;
import com.reto.usuario.infrastructure.driven.jpa.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IRolEntityMapper {

    RolModel toRolModel(RolEntity rolEntity);
}
