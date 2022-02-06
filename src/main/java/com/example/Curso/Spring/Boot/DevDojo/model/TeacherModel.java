package com.example.Curso.Spring.Boot.DevDojo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class TeacherModel extends AbstractyEntity {
    @NotNull
    @NotEmpty(message = "Nome do professor é obrigatorio")
    private String name ;

    @NotNull
    @NotEmpty(message = "Endereço de email é obrigatorio ")
    @Email
    private String Email;

    @ManyToOne()
    Student student;
    public TeacherModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Student getStudent() {

        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
