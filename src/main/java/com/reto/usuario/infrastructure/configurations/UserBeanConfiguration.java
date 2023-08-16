package com.reto.usuario.infrastructure.configurations;

import com.reto.usuario.domain.api.IUserUseCasePort;
import com.reto.usuario.domain.spi.persistence.IRolPersistenceDomainPort;
import com.reto.usuario.domain.spi.persistence.IUserPersistenceDomainPort;
import com.reto.usuario.domain.usecase.UserUseCase;
import com.reto.usuario.infrastructure.driven.jpa.mapper.IRolEntityMapper;
import com.reto.usuario.infrastructure.driven.jpa.mapper.IUserEntityMapper;
import com.reto.usuario.infrastructure.driven.jpa.adapter.RolPersistenceDomainPortImpl;
import com.reto.usuario.infrastructure.driven.jpa.adapter.UserPersistenceDomainPortImpl;
import com.reto.usuario.infrastructure.driven.jpa.repository.IRolRepositoryMysql;
import com.reto.usuario.infrastructure.driven.jpa.repository.IUserRepositoryMysql;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class UserBeanConfiguration {

    private final IUserRepositoryMysql userRepositoryMysql;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepositoryMysql rolRepositoryMysql;
    private final IRolEntityMapper rolEntityMapper;

    @Bean
    public IUserPersistenceDomainPort userPersistencePort() {
        return new UserPersistenceDomainPortImpl(userRepositoryMysql, userEntityMapper);
    }

    @Bean
    public IRolPersistenceDomainPort rolesPersistencePort() {
        return new RolPersistenceDomainPortImpl(rolRepositoryMysql, rolEntityMapper);
    }

    @Bean
    public IUserUseCasePort userUseCasePort() {
        return new UserUseCase(userPersistencePort(), rolesPersistencePort());
    }
}
