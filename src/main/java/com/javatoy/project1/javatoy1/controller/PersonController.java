package com.javatoy.project1.javatoy1.controller;

import com.javatoy.project1.javatoy1.controller.dto.PersonDto;
import com.javatoy.project1.javatoy1.domain.Person;
import com.javatoy.project1.javatoy1.exception.PersonNotFoundException;
import com.javatoy.project1.javatoy1.exception.RenameNotPermittedException;
import com.javatoy.project1.javatoy1.exception.dto.ErrorResponse;
import com.javatoy.project1.javatoy1.repository.PersonRepository;
import com.javatoy.project1.javatoy1.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody PersonDto personDto) {
        personService.put(personDto);
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name) {
        personService.modify(id, name);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }
}
