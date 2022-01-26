package com.bancopichincha.tienda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.entity.ClientEntity;
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
	
}
