package com.nttdata.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nttdata.prueba.model.dtos.AccountDto;
import com.nttdata.prueba.model.dtos.NewAccount;
import com.nttdata.prueba.model.dtos.UpdateAccount;
import com.nttdata.prueba.model.entities.Account;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IAccountService {

    /**
     * Crear nueva cuenta
     * 
     * @param newAccount
     */
    public ResponseEntity<Object> createAccount(@RequestBody NewAccount newAccount);

    /**
     * Editar cuenta
     * 
     * @param updateClient
     */
    public ResponseEntity<Object> updateAccount(Long id, UpdateAccount updateAccount);

    /**
     * Eliminar cuenta
     * 
     * @pathVar idAccount
     */
    public ResponseEntity<Object> deleteAccount(Long idAccount);

    /**
     * Obtener todas las cuentas
     * 
     */
    public List<AccountDto> getAllAccounts();

    /**
     * Obtener todas las cuentas de un cliente
     * 
     * @pathVariable idClient
     * 
     */
    public List<Account> getAllAccountsByClient(Long idClient);

    /**
     * Obtener cuenta por Id
     * 
     * @pathVariable idAccount
     * 
     */
    public Account getById(Long idClient);

}
