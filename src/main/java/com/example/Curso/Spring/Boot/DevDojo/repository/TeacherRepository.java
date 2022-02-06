package com.example.Curso.Spring.Boot.DevDojo.repository;

import com.example.Curso.Spring.Boot.DevDojo.model.TeacherModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeacherRepository extends PagingAndSortingRepository<TeacherModel , Long>
{
    TeacherModel findByName(String name);
}
