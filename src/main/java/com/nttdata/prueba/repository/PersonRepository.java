package com.nttdata.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.prueba.model.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
