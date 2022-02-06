package com.example.Curso.Spring.Boot.DevDojo.endpoint;

import com.example.Curso.Spring.Boot.DevDojo.model.School;
import com.example.Curso.Spring.Boot.DevDojo.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("school")
public class EndPointSchool
{


    private final SchoolRepository schoolRepository;
    @Autowired
    public EndPointSchool (SchoolRepository schoolRepository)
    {
        this.schoolRepository = schoolRepository;
    }
    @GetMapping
    public ResponseEntity<?>findSchool()
    {
        return new ResponseEntity<>(schoolRepository.findAll(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?>save(School school)
    {
        return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.CREATED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?>findById(@PathVariable("id")Long id )
    {
        return new ResponseEntity<>(schoolRepository.findById(id),HttpStatus.OK);
    }
}
