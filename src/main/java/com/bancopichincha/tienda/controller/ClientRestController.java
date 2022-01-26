package com.bancopichincha.tienda.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.entity.ClientEntity;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.services.IClientService;

@RestController
@RequestMapping("/api")
public class ClientRestController {

	@Autowired
	private IClientService clientService;

	@GetMapping("/client")
	public ResponseEntity<List<ClientEntity>> findAll() {
		return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/client/{numberIdentification}")
	public ResponseEntity<ClientEntity> findByNumberIdentification(@PathVariable String numberIdentification) {
		return new ResponseEntity<>(clientService.findByNumberIdentification(numberIdentification), HttpStatus.OK);
	}
	
	@PostMapping("/client")
	public ResponseEntity<HttpStatus> createClient(@Validated @RequestBody ClientRequest clientRequest) {
		clientService.create(clientRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/client")
	public ResponseEntity<HttpStatus> updateClient(@Validated @RequestBody ClientRequest clientRequest) {
		clientService.update(clientRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/client/{numberIdentification}")
	public ResponseEntity<HttpStatus> deleteClient(@PathVariable String numberIdentification) {
		clientService.delete(numberIdentification);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/client/photo")
	public ResponseEntity<HttpStatus> updatePhoto(@RequestPart(name = "file") MultipartFile file, @RequestPart("numberIdentification") String numberIdentification) {
		clientService.updatePhoto(file, numberIdentification);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/client/photo/{numberIdentification}")
	public ResponseEntity<Resource> uploadsPhoto(@PathVariable String numberIdentification) throws MalformedURLException {
		ClientEntity  clientEntity= clientService.findByNumberIdentification(numberIdentification);
		Path nameFile = Paths.get("uploads").resolve(clientEntity.getPhoto()).toAbsolutePath();
		Resource resource = new UrlResource(nameFile.toUri());
		if(!resource.exists() && !resource.isReadable()) {
			throw new DataException(ProductEntity.class, "Error no se pudo cargar la imagen", nameFile);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
	}
	
}
