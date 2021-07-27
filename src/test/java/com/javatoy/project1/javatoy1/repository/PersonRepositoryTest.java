package com.javatoy.project1.javatoy1.repository;

import com.javatoy.project1.javatoy1.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud() {
        Person person = new Person();
        person.setName("wonos");
        person.setAge(30);
        person.setBloodType("O");

        personRepository.save(person);

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("wonos");
        assertThat(people.get(0).getAge()).isEqualTo(30);
        assertThat(people.get(0).getBloodType()).isEqualTo("O");
    }

    @Test
    void hashCodeAndEquals() {
        Person person1 = new Person("martin", 10, "0");
        Person person2 = new Person("martin", 10, "A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBloodType() {
        givenPerson("martin", 10, "A");
        givenPerson("wonho", 30, "O");
        givenPerson("saet", 33, "B");
        givenPerson("bul", 40, "AB");

        List<Person> result = personRepository.findByBloodType("O");

        result.forEach(System.out::println);

    }

    @Test
    void findByBirthdayBetween() {
        givenPerson("martin", 10, "A", LocalDate.of(1993,06,18));
        givenPerson("wonho", 30, "O", LocalDate.of(1992,5,19));
        givenPerson("saet", 33, "B", LocalDate.of(1990,5,11));
        givenPerson("bul", 40, "AB", LocalDate.of(1998,7,1));
        givenPerson("seong", 50, "B", LocalDate.of(1997,9,4));
        givenPerson("min", 60, "O", LocalDate.of(1991,3,5));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1992,5,19), LocalDate.of(2002,6,18));

        result.forEach(System.out::println);
    }

    private void givenPerson(String name, int age, String bloodType) {
        givenPerson(name, age, bloodType, null);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);

        personRepository.save(person);
    }
}
