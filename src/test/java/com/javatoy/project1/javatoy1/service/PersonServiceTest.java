package com.javatoy.project1.javatoy1.service;

import com.javatoy.project1.javatoy1.domain.Block;
import com.javatoy.project1.javatoy1.domain.Person;
import com.javatoy.project1.javatoy1.repository.BlockRepository;
import com.javatoy.project1.javatoy1.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        givenPeople();
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();
        //System.out.println(result);

        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest() {
        givenPeople();
        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
    }

    private void givenPeople() {
        givenPerson("wonos", 30, "O");
        givenPerson("hee", 30, "A");
        givenPerson("chul", 30, "A");
        givenPerson("wonos", 22, "AB");
        givenBlockPerson("wonos", 30, "0");
    }

    private void givenBlocks() {
        givenBlock("wonos");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }

    private void givenBlockPerson(String name, int age, String bloodType) {
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));
    }

    private Block givenBlock(String name) {
       return blockRepository.save(new Block(name));
    }

}
