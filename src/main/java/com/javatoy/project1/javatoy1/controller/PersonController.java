package com.javatoy.project1.javatoy1.controller;

import com.javatoy.project1.javatoy1.domain.Person;
import com.javatoy.project1.javatoy1.repository.PersonRepository;
import com.javatoy.project1.javatoy1.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {

        personService.put(person);

        log.info("Person -> {} ", personRepository.findAll());
    }
}
