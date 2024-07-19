package com.nttdata.prueba.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nttdata.prueba.config.AES256;
import com.nttdata.prueba.exception.DataAccessCustomException;
import com.nttdata.prueba.exception.ModelNotFoundException;
import com.nttdata.prueba.exception.NotFoundException;
import com.nttdata.prueba.model.dtos.ClientDto;
import com.nttdata.prueba.model.dtos.NewClient;
import com.nttdata.prueba.model.dtos.UpdateClient;
import com.nttdata.prueba.model.entities.Client;
import com.nttdata.prueba.repository.ClientRepository;
import com.nttdata.prueba.service.IClientService;

@Service
public class ClientServiceImp implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LoggerService logger;

    @Autowired
    private AES256 aes256;

    @Override
    public ResponseEntity<Object> registerClient(NewClient newClient) {
        Client clientRegister = new Client();
        String passwordEncrypt = "";
        clientRegister.setAddress(newClient.getAddress());
        clientRegister.setAge(newClient.getAge());
        if (newClient.getGender() != null && !newClient.getGender().equals("")) {
            clientRegister.setGender(newClient.getGender());
        }
        clientRegister.setIdentification(newClient.getIdentification());
        clientRegister.setName(newClient.getName());
        if (newClient.getPhone() != null && !newClient.getPhone().equals("")) {
            clientRegister.setPhone(newClient.getPhone());
        }
        passwordEncrypt = aes256.toAES256(newClient.getPassword());
        clientRegister.setPassword(passwordEncrypt);
        clientRegister.setStatus(true);
        try {
            clientRepository.save(clientRegister);
            logger.msgInfo(null, "Cliente Registrado", null, null);
        } catch (DataAccessException e) {
            logger.buildError(getClass().getName(), "save", "Error en el registro del cliente", e.getMessage(),
                    HttpStatus.BAD_REQUEST.toString());
            throw new DataAccessCustomException("Error en el registro del cliente. DataAccess", e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Cliente Registrado!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateClient(Long id, UpdateClient updateClient) {
        Client clientTemp = this.getById(id);
        if (clientTemp == null) {
            throw new ModelNotFoundException(String.format("Cliente con id: %d no encontrado", id),
                    "El cliente no existe", HttpStatus.NOT_FOUND);
        }
        String passwordEncrypt = "";
        if (updateClient.getAddress() != null &&
                !updateClient.getAddress().equals("")) {
            clientTemp.setAddress(updateClient.getAddress());
        }
        if (updateClient.getAge() != null) {
            clientTemp.setAge(updateClient.getAge());
        }
        if (updateClient.getGender() != null && !updateClient.getGender().equals("")) {
            clientTemp.setGender(updateClient.getGender());
        }
        if (updateClient.getName() != null && !updateClient.getName().equals("")) {
            clientTemp.setName(updateClient.getName());
        }
        if (updateClient.getPhone() != null && !updateClient.getPhone().equals("")) {
            clientTemp.setPhone(updateClient.getPhone());
        }
        if (updateClient.getPassword() != null &&
                !updateClient.getPassword().equals("")) {
            passwordEncrypt = aes256.toAES256(updateClient.getPassword());
            clientTemp.setPassword(passwordEncrypt);
        }
        if (updateClient.getStatus() != null) {
            clientTemp.setStatus(updateClient.getStatus());
        }

        try {
            clientRepository.save(clientTemp);
            logger.msgInfo(null, "Cliente editado", null, null);
        } catch (DataAccessException e) {
            logger.buildError(getClass().getName(), "update", "Error al editar el cliente", e.getMessage(),
                    HttpStatus.BAD_REQUEST.toString());
            throw new DataAccessCustomException("Error al modificar el cliente. DataAccess", e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente Modificado!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteClient(Long idClient) {
        Client optionalClient = this.getById(idClient);
        if (optionalClient == null) {
            throw new ModelNotFoundException(String.format("Cliente con id: %d no encontrado", idClient),
                    "El cliente que desea eliminar no existe", HttpStatus.NOT_FOUND);
        }
        try {
            clientRepository.deleteById(idClient);
            logger.msgInfo(null, "Cliente elimninado", null, null);
        } catch (DataAccessException e) {
            logger.buildError(getClass().getName(), "delete", "Error al eliminar el cliente", e.getMessage(),
                    HttpStatus.BAD_REQUEST.toString());
            throw new DataAccessCustomException("Error al eliminar el cliente. DataAccess", e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cliente Eliminado!", HttpStatus.OK);
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDto> response = new ArrayList<>();
        for (Client client : clients) {
            ClientDto temp = ClientDto.builder().address(client.getAddress()).name(client.getName())
                    .phone(client.getPhone()).password(client.getPassword())
                    .status(client.getStatus() ? "Activo" : "Inactivo").build();
            response.add(temp);
        }
        return response;
    }

    @Override
    public Client getById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            throw new NotFoundException("Cliente con Id: " + id + " no encontrado",
                    " cliente no encontrado, proporcione un id correcto", HttpStatus.BAD_REQUEST);
        }
        return optionalClient.get();
    }

}
