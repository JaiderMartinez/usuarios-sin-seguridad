package com.reto.usuario.domain.usecase;

import com.reto.usuario.domain.api.IUserUseCasePort;
import com.reto.usuario.domain.exceptions.EmailExistsException;
import com.reto.usuario.domain.exceptions.RolNotFoundException;
import com.reto.usuario.domain.exceptions.UserNotFoundException;
import com.reto.usuario.domain.model.RolModel;
import com.reto.usuario.domain.model.UserModel;
import com.reto.usuario.domain.spi.persistence.IRolPersistenceDomainPort;
import com.reto.usuario.domain.spi.persistence.IUserPersistenceDomainPort;
import com.reto.usuario.domain.exceptions.EmailNotFoundException;

public class UserUseCase implements IUserUseCasePort {

    private final IUserPersistenceDomainPort userPersistenceDomainPort;
    private final IRolPersistenceDomainPort rolPersistenceDomainPort;
    private static final String ROLE_OWNER = "PROPIETARIO";
    private static final String ROLE_EMPLOYEE = "EMPLEADO";
    private static final String ROLE_CUSTOMER = "CLIENTE";

    public UserUseCase(IUserPersistenceDomainPort userPersistenceDomainPort, IRolPersistenceDomainPort rolesPersistenceDomainPort) {
        this.userPersistenceDomainPort = userPersistenceDomainPort;
        this.rolPersistenceDomainPort = rolesPersistenceDomainPort;
    }

    @Override
    public UserModel registerUserWithOwnerRole(UserModel userModel) {
        restrictionsWhenSavingAUser(userModel);
        final RolModel rolOwnerFound = this.rolPersistenceDomainPort.findByName(ROLE_OWNER);
        userModel.setRol(rolOwnerFound);
        return this.userPersistenceDomainPort.saveUser(userModel);
    }

    @Override
    public UserModel registerUserWithEmployeeRole(UserModel userModel) {
        restrictionsWhenSavingAUser(userModel);
        final RolModel rolEmployeeFound = findRoleByIdAndCompareRoleName(ROLE_EMPLOYEE, userModel.getRol().getIdRol());
        userModel.setRol(rolEmployeeFound);
        return this.userPersistenceDomainPort.saveUser(userModel);
    }

    @Override
    public UserModel registerUserWithCustomerRole(UserModel userCustomerRequest) {
        restrictionsWhenSavingAUser(userCustomerRequest);
        final RolModel roleCustomerFound = findRoleByIdAndCompareRoleName(ROLE_CUSTOMER, userCustomerRequest.getRol().getIdRol());
        userCustomerRequest.setRol(roleCustomerFound);
        return this.userPersistenceDomainPort.saveUser(userCustomerRequest);
    }

    private RolModel findRoleByIdAndCompareRoleName(String roleName, Long idRol) {
        RolModel rolModel = this.rolPersistenceDomainPort.findByIdRol(idRol);
        if(rolModel == null) {
            throw new RolNotFoundException();
        }
        if(!rolModel.getName().equals(roleName) ) {
            throw new RolNotFoundException();
        }
        return rolModel;
    }

    private void restrictionsWhenSavingAUser(UserModel userModel) {
        if(userPersistenceDomainPort.existsByEmail(userModel.getEmail())) {
            throw new EmailExistsException();
        }
        userModel.validateEmailStructure();
        userModel.validateFieldsEmpty();
        userModel.validateUserCellPhone();
    }

    @Override
    public UserModel findUserByEmail(String email) {
        UserModel userModel = userPersistenceDomainPort.findByEmail(email);
        if (userModel == null) {
            throw new EmailNotFoundException();
        }
        return userModel;
    }

    @Override
    public UserModel getUserById(Long idUser) {
        UserModel userModel = userPersistenceDomainPort.findById(idUser);
        if(userModel == null) {
            throw new UserNotFoundException();
        }
        return userModel;
    }
}

