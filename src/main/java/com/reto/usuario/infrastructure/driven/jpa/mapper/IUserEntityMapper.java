package com.reto.usuario.infrastructure.driven.jpa.mapper;

import com.reto.usuario.domain.model.UserModel;
import com.reto.usuario.infrastructure.driven.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface IUserEntityMapper {

    UserEntity toUserEntity(UserModel userModel);

    UserModel toUserModel(UserEntity userEntity);
}
