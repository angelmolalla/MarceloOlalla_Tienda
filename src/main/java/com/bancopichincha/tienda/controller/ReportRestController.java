package com.bancopichincha.tienda.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.services.IReportService;

@RestController
@RequestMapping("/api")
public class ReportRestController {

	@Autowired
	private IReportService reportService;

	@GetMapping("/report/count/{codStore}/{date}")
	public Map<String, String> findCountByStoreAndDate(@PathVariable String codStore,
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws MalformedURLException {
		HashMap<String, String> map = new HashMap<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Long count = reportService.findCountByStoreAndDate(codStore, date);
		map.put("codStore", codStore);
		map.put("date", formatter.format(date));
		map.put("count", count.toString());
		return map;
	}

	@GetMapping("/report/amount/{codStore}/{codProduc}")
	public Map<String, String> amountSoldbyStoreAndProduct(@PathVariable String codStore,
			@PathVariable String codProduc) throws MalformedURLException {
		HashMap<String, String> map = new HashMap<>();

		Double amountSold = reportService.amountSoldbyStoreAndProduct(codStore, codProduc);
		map.put("codStore", codStore);
		map.put("codProduc", codProduc);
		map.put("amountSold", amountSold.toString());
		return map;
	}

	@GetMapping("/report/csv/{numberIdentification}/{minDate}/{maxDate}")
	public ResponseEntity<Resource> uploadsPhoto(@PathVariable String numberIdentification,
			@PathVariable("minDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDate,
			@PathVariable("maxDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDate)
			throws MalformedURLException {
		String name = reportService.findListByIdentificationAndDate(numberIdentification, minDate, maxDate);

		Path nameFile = Paths.get("uploadsCSV").resolve(name).toAbsolutePath();
		Resource resource = new UrlResource(nameFile.toUri());
		if (!resource.exists() && !resource.isReadable()) {
			throw new DataException(ProductEntity.class, "Error no se pudo cargar el archivo csv", nameFile);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
