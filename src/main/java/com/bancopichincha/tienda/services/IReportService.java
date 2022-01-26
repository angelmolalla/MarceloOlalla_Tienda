package com.bancopichincha.tienda.services;

import java.util.Date;

public interface IReportService {

	public Long findCountByStoreAndDate(String codStore,Date date);
	
	public Double amountSoldbyStoreAndProduct(String codStore,String codProduc);
	
	public String findListByIdentificationAndDate(String numberIdentification, Date minDate, Date maxDate);
}
