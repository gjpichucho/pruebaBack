package com.nttdata.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.nttdata.prueba.model.dtos.ClientDto;
import com.nttdata.prueba.model.dtos.NewClient;
import com.nttdata.prueba.model.dtos.UpdateClient;
import com.nttdata.prueba.model.entities.Client;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IClientService {

    /**
     * Registrar nuevo cliente
     * 
     * @param newClient
     */
    public ResponseEntity<Object> registerClient(@RequestBody NewClient newClient);

    /**
     * Editar cliente
     * 
     * @param updateClient
     */
    public ResponseEntity<Object> updateClient(@PathVariable("id") Long id, @RequestBody UpdateClient updateClient);

    /**
     * Eliminar cliente
     * 
     * @pathVar idClient
     */
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long idClient);

    /**
     * Obtener todos los clientes
     * 
     */
    public List<ClientDto> getAllClients();

    /**
     * Obtener un cliente por su id
     * 
     * @pathVar idClient
     */
    public Client getById(Long id);
}
