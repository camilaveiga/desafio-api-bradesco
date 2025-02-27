package com.example.desafio_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.usertype.UserType;

import java.util.Date;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome nao pode estar vazio")
    @Size(min = 3, max = 50, message = "Nome invalido")
    private String fullName;

    @Column(nullable = false, unique = true)
    @Email(message = "email inavalido")
    private String email;

    @Column(nullable = false, unique = false)
    @Pattern(regexp = "\\+55\\s\\d{2}\\s\\d{5}-\\d{4}",
    message = "O telefone deve estar no formato +55 99 99999-9999")
    private String phone;

    @NotNull(message = "Data nao pode ser vazio")
    private Date birthDate;

    @NotNull(message = "UserType nao pode ser vazio")
    private UserType userType;

    public enum UserType{
        ADMIN,
        EDITOR,
        VIEWER
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
