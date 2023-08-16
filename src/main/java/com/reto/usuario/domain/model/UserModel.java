package com.reto.usuario.domain.model;

import com.reto.usuario.domain.exceptions.EmptyFieldsException;
import com.reto.usuario.domain.exceptions.InvalidCellPhoneFormatException;
import com.reto.usuario.domain.exceptions.InvalidEmailFormatException;

public class UserModel {

    private Long idUser;
    private String name;
    private String lastName;
    private String identificationDocument;
    private String cellPhone;
    private String email;
    private String password;
    private RolModel rol;

    public UserModel(){}

    public UserModel(Long idUser, String name, String lastName, String identificationDocument, String cellPhone, String email, String password, RolModel rol) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.identificationDocument = identificationDocument;
        this.cellPhone = cellPhone;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(String identificationDocument) {
        this.identificationDocument = identificationDocument;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolModel getRol() {
        return rol;
    }

    public void setRol(RolModel rol) {
        this.rol = rol;
    }

    public void validateEmailStructure() {
        int atPosition = email.lastIndexOf( '@' );
        int dotPosition = email.lastIndexOf( '.' );
        if( email.endsWith(".") || atPosition == -1 || dotPosition == -1 ||
                dotPosition < atPosition ) {
            throw new InvalidEmailFormatException();
        }

    }

    public void validateFieldsEmpty() {
        if( this.identificationDocument == null || this.name.replace(" ", "").isEmpty() ||
                this.lastName.replace(" ", "").isEmpty() ||
                this.cellPhone.replace(" ", "").isEmpty() ||
                this.email.replace(" ", "").isEmpty() ||
                this.password.replace(" ", "").isEmpty() ) {
            throw new EmptyFieldsException();
        }
    }

    public void validateUserCellPhone() {
        String phoneUserNoSpaces = this.cellPhone.replace(" ", "").trim();
        if(phoneUserNoSpaces.startsWith("+")){
            if(!phoneUserNoSpaces.matches("\\+\\d+") || phoneUserNoSpaces.length() != 13 ) {
                throw new InvalidCellPhoneFormatException();
            }
        } else {
            if (phoneUserNoSpaces.length() != 10 || !phoneUserNoSpaces.matches("\\d+")) {
                throw new InvalidCellPhoneFormatException();
            }
        }
    }
}
