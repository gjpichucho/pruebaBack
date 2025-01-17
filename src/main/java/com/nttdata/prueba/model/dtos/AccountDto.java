package com.nttdata.prueba.model.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    String nameClient;

    String numberAccount;

    BigDecimal initialBalance;

    String typeAccount;

    String status;
    
    List<MovementDto> movements;

}
