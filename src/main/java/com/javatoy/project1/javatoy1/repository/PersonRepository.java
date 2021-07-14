package com.javatoy.project1.javatoy1.repository;

import com.javatoy.project1.javatoy1.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
