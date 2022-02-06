package com.example.Curso.Spring.Boot.DevDojo.endpoint;

import com.example.Curso.Spring.Boot.DevDojo.error.NameNotFoundException;
import com.example.Curso.Spring.Boot.DevDojo.error.ResourceNotFoundException;
import com.example.Curso.Spring.Boot.DevDojo.model.TeacherModel;
import com.example.Curso.Spring.Boot.DevDojo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("teacher")
@RestController
public class EndPointTeacher {

    private final TeacherRepository teacherRepository;

    @Autowired
    public EndPointTeacher(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/teacherList")
    public ResponseEntity<?> saveList(List<TeacherModel> list) {

        return new ResponseEntity<>(teacherRepository.saveAll(list), HttpStatus.CREATED);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@RequestBody @Valid TeacherModel teacherModel) {
        verifyIfTeacherNotExists(teacherModel.getId());
        return new ResponseEntity<>(teacherRepository.save(teacherModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(TeacherModel teacherModel) {

        return new ResponseEntity<>(teacherRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        verifyIfTeacherNotExists(id);
        Optional<TeacherModel> byId = teacherRepository.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping(path = "findByName/{name}")
    public ResponseEntity<?> findTeacherByName(@PathVariable("name") String name) {
        verifyIfTeacherNotExists(name);
        return new ResponseEntity<>(teacherRepository.findByName(name), HttpStatus.OK);
    }

    private void verifyIfTeacherNotExists(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id nao encontrado ");
        }
    }

    private void verifyIfTeacherExists(Long id) {
        if (teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Id ja existe  ");
        }
    }

    private void verifyIfTeacherNotExists(String name) {
        if (teacherRepository.findByName(name) == null) {
            throw new NameNotFoundException("Nome nao encontrado");
        }
    }
}
