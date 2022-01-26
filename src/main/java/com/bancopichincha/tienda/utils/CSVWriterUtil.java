package com.bancopichincha.tienda.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bancopichincha.tienda.entity.OrderTransactionEntity;
import com.opencsv.CSVWriter;

public class CSVWriterUtil {

	private static final Logger log_logger = Logger.getLogger(CSVWriterUtil.class.getName());

	public String saveCSV(List<OrderTransactionEntity> list) {
		Integer cont = 1;
		String nameFile = UUID.randomUUID() + ".csv";
		File file = new File("uploadsCSV/" + nameFile);

		try {
			FileWriter output = new FileWriter(file);
			CSVWriter write = new CSVWriter(output);
			String[] header = { "Number", "Client", "Store", "Product", "Amount", "Date","Time" };
			write.writeNext(header);

			for (OrderTransactionEntity orderTransaction : list) {
				String[] data = { cont.toString(), orderTransaction.getClientEntity().getName(),
						orderTransaction.getStoreEntity().getName(), orderTransaction.getProductEntity().getName(),
						orderTransaction.getAmount().toString(), orderTransaction.getCreationDate().toLocalDate().toString(),
						orderTransaction.getCreationDate().toLocalTime().toString()};
				write.writeNext(data);
				cont = cont + 1;
			}

			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log_logger.log(Level.INFO, "Archivo csv creado");
		return nameFile;

	}

}
