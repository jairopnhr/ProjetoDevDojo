package com.example.Curso.Spring.Boot.DevDojo.repository;

import com.example.Curso.Spring.Boot.DevDojo.model.School;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolRepository extends PagingAndSortingRepository<School,Long>
{

}
