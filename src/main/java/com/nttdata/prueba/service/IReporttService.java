package com.nttdata.prueba.service;

import com.nttdata.prueba.model.dtos.ReportDto;

public interface IReporttService {
	
    /**
     * retorna el estado de cuenta de un cliente
     * @param idClient
     * @param endDate 
     * @param initialDate 
     * @return
     */
	public ReportDto getStatusAccountByClient(Long idClient, String initialDate, String endDate);


}
