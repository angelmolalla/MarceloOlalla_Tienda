package com.bancopichincha.tienda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bancopichincha.tienda.dto.StoreRequest;
import com.bancopichincha.tienda.entity.StoreEntity;
import com.bancopichincha.tienda.services.IStoreService;

@RestController
@RequestMapping("/api")
public class StoreRestController {
	
	@Autowired
	private IStoreService storeService;

	@GetMapping("/store")
	public ResponseEntity<List<StoreEntity>> findAll() {
		return new ResponseEntity<>(storeService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("store/prodAssignment")
	public ResponseEntity<HttpStatus> productAssignment(@Validated @RequestBody StoreRequest storeRequest) {
		storeService.prodAssignment(storeRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
