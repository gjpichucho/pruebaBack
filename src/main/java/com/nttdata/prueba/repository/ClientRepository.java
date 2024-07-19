package com.nttdata.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.prueba.model.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
