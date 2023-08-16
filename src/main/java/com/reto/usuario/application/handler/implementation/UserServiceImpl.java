package com.reto.usuario.application.handler.implementation;

import com.reto.usuario.application.dto.request.UserCustomerRequestDto;
import com.reto.usuario.application.dto.response.UserCustomerResponseDto;
import com.reto.usuario.application.dto.request.UserOwnerRequestDto;
import com.reto.usuario.application.dto.request.UserEmployeeRequestDto;
import com.reto.usuario.application.dto.response.UserEmployeeResponseDto;
import com.reto.usuario.application.dto.response.UserOwnerResponseDto;
import com.reto.usuario.application.dto.response.UserResponseDto;
import com.reto.usuario.application.dto.response.UserWithFieldIdUserResponseDto;
import com.reto.usuario.application.handler.IUserService;
import com.reto.usuario.application.mapper.request.IUserRequestMapper;
import com.reto.usuario.application.mapper.response.IUserResponseMapper;
import com.reto.usuario.domain.api.IUserUseCasePort;
import com.reto.usuario.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final IUserUseCasePort userUseCasePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Transactional
    @Override
    public UserOwnerResponseDto registerUserWithOwnerRole(UserOwnerRequestDto userRequestDto) {
        final UserModel userRequestModel = this.userRequestMapper.toUserModel(userRequestDto);
        final UserModel userRegisteredModel = this.userUseCasePort.registerUserWithOwnerRole(userRequestModel );
        return this.userResponseMapper.userModelToUserOwnerResponseDto(userRegisteredModel );
    }

    @Transactional
    @Override
    public UserEmployeeResponseDto registerUserWithEmployeeRole(UserEmployeeRequestDto userRequestToCreateEmployeeDto) {
        final UserModel userEmployeeRequestModel = this.userRequestMapper.userEmployeeRequestDtoUserModel(userRequestToCreateEmployeeDto);
        final UserModel userEmployeeRegisteredModel = this.userUseCasePort.registerUserWithEmployeeRole(userEmployeeRequestModel);
        return userResponseMapper.userModelToUserEmployeeResponseDto(userEmployeeRegisteredModel);
    }

    @Transactional
    @Override
    public UserCustomerResponseDto registerUserWithCustomerRole(UserCustomerRequestDto userCustomerRequestDto) {
        final UserModel userCustomerRequestModel = this.userRequestMapper.userCustomerRequestDtoToUserModel(userCustomerRequestDto);
        final UserModel userCustomerRegisteredModel = this.userUseCasePort.registerUserWithCustomerRole(userCustomerRequestModel);
        return this.userResponseMapper.userModelToUserCustomerResponseDto(userCustomerRegisteredModel);
    }

    @Override
    public UserResponseDto getUserById(Long idUser) {
        return userResponseMapper.userModeltoUserResponseDto(userUseCasePort.getUserById(idUser));
    }

    @Override
    public UserWithFieldIdUserResponseDto getUserByUniqueEmail(String email) {
        return this.userResponseMapper.userModelToUserWithFieldIdUserResponseDto(this.userUseCasePort.findUserByEmail(email));
    }
}
