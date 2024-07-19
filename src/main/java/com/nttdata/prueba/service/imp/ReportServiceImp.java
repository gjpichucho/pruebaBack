package com.nttdata.prueba.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.prueba.model.dtos.AccountDto;
import com.nttdata.prueba.model.dtos.MovementDto;
import com.nttdata.prueba.model.dtos.ReportDto;
import com.nttdata.prueba.model.entities.Account;
import com.nttdata.prueba.service.IAccountService;
import com.nttdata.prueba.service.IMovementService;
import com.nttdata.prueba.service.IReporttService;

@Service
public class ReportServiceImp implements IReporttService{
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IMovementService movementsService;

	@Override
	public ReportDto getStatusAccountByClient(Long idClient, String initialDate, String endDate) {
		List<Account> allAccountsByClient = accountService.getAllAccountsByClient(idClient);
		ReportDto report = new ReportDto();
		List<AccountDto> listResult = new ArrayList<>();
		allAccountsByClient.forEach(account -> {
			List<MovementDto> movementsByAccount = movementsService.getAllMovementsByAccountAndDate(account.getId(), initialDate, endDate);
			AccountDto temp = AccountDto.builder().initialBalance(account.getInitialBalance())
                    .numberAccount(account.getAccountNumber())
                    .nameClient(account.getClient().getName()).status(account.getStatus() ? "Activa" : "Inactiva")
                    .typeAccount(account.getTypeAccount().name()).build();
			temp.setMovements(movementsByAccount);
			listResult.add(temp);
			report.setAccount(listResult);
		});
		report.setAccount(listResult);
		return report;
	}

}
