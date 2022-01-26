package com.bancopichincha.tienda.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bancopichincha.tienda.dao.IClientDao;
import com.bancopichincha.tienda.dao.IOrderTransactionDao;
import com.bancopichincha.tienda.entity.ClientEntity;
import com.bancopichincha.tienda.entity.OrderTransactionEntity;
import com.bancopichincha.tienda.exception.IntegrityViolationException;
import com.bancopichincha.tienda.exception.NotFoundException;
import com.bancopichincha.tienda.utils.CSVWriterUtil;

@Service
public class ReportService implements IReportService {

	@Autowired
	private IOrderTransactionDao orderTransactionDao;

	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public Long findCountByStoreAndDate(String codStore, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(formatter.format(date));
		LocalTime localTimeMin = LocalTime.MIN;
		LocalTime localTimeMax = LocalTime.MAX;
		LocalDateTime localDateTimeMin = LocalDateTime.of(localDate, localTimeMin);
		LocalDateTime localDateTimeMax = LocalDateTime.of(localDate, localTimeMax);
		return orderTransactionDao.findCountByStoreAndDate(codStore, localDateTimeMin, localDateTimeMax).orElse(0L);
	}

	@Override
	@Transactional(readOnly = true)
	public Double amountSoldbyStoreAndProduct(String codStore, String codProduc) {
		List<OrderTransactionEntity> orderTransactionEntities = orderTransactionDao
				.findListByStoreAndProduct(codStore, codProduc).orElse(new ArrayList<>());
		
		Double amountSold = orderTransactionEntities.stream()
				.mapToDouble(i -> i.getAmount() * i.getProductEntity().getPrice()).sum();
		return amountSold;
	}

	@Override
	@Transactional(readOnly = true)
	public String findListByIdentificationAndDate(String numberIdentification, Date minDate, Date maxDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate minLocalDate = LocalDate.parse(formatter.format(minDate));
		LocalDate maxLocalDate = LocalDate.parse(formatter.format(maxDate));
		if (minLocalDate.isAfter(maxLocalDate)) {
			throw new IntegrityViolationException(LocalDate.class, "minLocalDate", minLocalDate);
		}
		LocalTime minLocalTime = LocalTime.of(0, 0, 0, 0);
		LocalTime maxLocalTime = LocalTime.of(0, 0, 0, 0);

		LocalDateTime minLocalDateTime = LocalDateTime.of(minLocalDate, minLocalTime);
		LocalDateTime maxLocalDateTime = LocalDateTime.of(maxLocalDate, maxLocalTime);

		ClientEntity clientEntity = clientDao.findByNumberIdentification(numberIdentification).orElseThrow(
				() -> new NotFoundException(ClientEntity.class, "numberIdentification", numberIdentification));
		List<OrderTransactionEntity> orderTransactionEntities = orderTransactionDao.findListByIdentificationAndDate(
				clientEntity.getNumberIdentification(), minLocalDateTime, maxLocalDateTime).orElse(new ArrayList<>());
		CSVWriterUtil csvWriter = new CSVWriterUtil();
		return csvWriter.saveCSV(orderTransactionEntities);
	}

}
