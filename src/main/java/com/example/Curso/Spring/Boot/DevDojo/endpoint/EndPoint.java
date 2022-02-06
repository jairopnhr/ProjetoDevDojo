package com.example.Curso.Spring.Boot.DevDojo.endpoint;

import com.example.Curso.Spring.Boot.DevDojo.error.ResourceNotFoundException;
import com.example.Curso.Spring.Boot.DevDojo.model.Student;
import com.example.Curso.Spring.Boot.DevDojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("students")
@RestController
public class EndPoint
{

    private final StudentRepository repository;
    @Autowired
    public EndPoint(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable)
    {
        return new  ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id)
    {
        verifyIfStudentExists(id);
        Optional<Student> student = repository.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?>save(@RequestBody @Valid  Student student)
    {

        return new  ResponseEntity<>(repository.save(student),HttpStatus.CREATED);
    }
    @GetMapping(path = "/FindByName/{name}")
    public ResponseEntity<?>findStudentByName(@PathVariable String name)
    {
        return new ResponseEntity<>(repository.findByNameIgnoreCaseContaining(name),HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?>delete(@PathVariable Long id)
    {
        verifyIfStudentExists(id);
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?>update(@RequestBody Student student)
    {
        verifyIfStudentExists(student.getId());
        repository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void verifyIfStudentExists(Long id)
    {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Id Nao Encontrado :"+id);
        }
    }
}
