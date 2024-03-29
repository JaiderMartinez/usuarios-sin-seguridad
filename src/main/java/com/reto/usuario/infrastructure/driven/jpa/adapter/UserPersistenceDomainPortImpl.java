package com.reto.usuario.infrastructure.driven.jpa.adapter;

import com.reto.usuario.domain.model.UserModel;
import com.reto.usuario.domain.spi.persistence.IUserPersistenceDomainPort;
import com.reto.usuario.infrastructure.driven.jpa.mapper.IUserEntityMapper;
import com.reto.usuario.infrastructure.driven.jpa.repository.IUserRepositoryMysql;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceDomainPortImpl implements IUserPersistenceDomainPort {

    private final IUserRepositoryMysql userRepositoryMysql;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userEntityMapper.toUserModel(
                userRepositoryMysql.save(userEntityMapper.toUserEntity(userModel)));
    }

    @Override
    public UserModel findByEmail(String email) {
        return userEntityMapper.toUserModel(
                userRepositoryMysql.findByEmail(email).orElse(null));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryMysql.existsByEmail(email);
    }

    @Override
    public UserModel findById(Long idUser) {
        return userEntityMapper.toUserModel(
                userRepositoryMysql.findById(idUser).orElse(null));
    }
}
