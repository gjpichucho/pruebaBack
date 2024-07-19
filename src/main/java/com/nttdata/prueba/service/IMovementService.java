package com.nttdata.prueba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nttdata.prueba.model.dtos.MovementDto;
import com.nttdata.prueba.model.dtos.NewMovement;
import com.nttdata.prueba.model.dtos.UpdateMovement;
import com.nttdata.prueba.model.entities.Movement;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface IMovementService {

    /**
     * Crear nuevo movimiento
     * 
     * @param newAccount
     */
    public ResponseEntity<Object> creaMovement(NewMovement newAccount);

    /**
     * Editar movimiento
     * 
     * @param updateMovement
     */
    public ResponseEntity<Object> updatMovement(Long id,
            @RequestBody UpdateMovement updateMovement);

    /**
     * Eliminar movimiento
     * 
     * @pathVar idMovement
     */
    public ResponseEntity<Object> deleteMovement(Long idMovement);

    /**
     * Obtener todas los movimientos de una cuenta
     * 
     * @pathVar idAccount
     * 
     */
    public List<MovementDto> getAllMovementsByAccount(Long idAccount);

    /**
     * Obtener todas los movimientos de una cuenta por un rango de fecha
     * 
     * @pathVar idAccount
     * 
     */
    public List<MovementDto> getAllMovementsByRangeDate(List<Movement> listMovement);

    /**
     * Obtener movimiento de una cuenta por su id
     * 
     * @pathVar idMovement
     * 
     */
    public Movement getById(Long idMovement);

	List<MovementDto> getAllMovementsByAccountAndDate(Long idAccount, String initialDate, String endDate);
}
