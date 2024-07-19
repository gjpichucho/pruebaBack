package com.nttdata.prueba.model.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDto {

    String address;

    String name;

    String password;

    String phone;

    String status;
}
