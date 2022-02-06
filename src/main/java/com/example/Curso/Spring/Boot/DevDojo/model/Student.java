package com.example.Curso.Spring.Boot.DevDojo.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends AbstractyEntity
{
    public Student() {
    }
    @NotEmpty(message = "Nome do aluno é obrigatorio ")
    @NotNull
    private String name;
    @NotEmpty(message = "Nome do email é obrigatorio ")
    @NotNull
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
