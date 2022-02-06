package com.example.Curso.Spring.Boot.DevDojo.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
public class School extends AbstractyEntity
{
    @NotNull
    @NotEmpty(message = "Nome obriagtorio")
    private String name;

    @NotEmpty(message = "Nome de Email é Obrigatorio")
    @Email
    private String email;
    @NotEmpty(message = "Numero de telefone é obriagtorio ")
    private String Phone;

    public School() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
