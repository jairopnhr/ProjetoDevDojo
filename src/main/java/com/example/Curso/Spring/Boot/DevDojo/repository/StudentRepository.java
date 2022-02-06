package com.example.Curso.Spring.Boot.DevDojo.repository;

import com.example.Curso.Spring.Boot.DevDojo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student,Long> {
Student findByNameIgnoreCaseContaining(String name);

}
