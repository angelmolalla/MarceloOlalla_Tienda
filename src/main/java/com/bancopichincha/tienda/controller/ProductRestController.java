package com.bancopichincha.tienda.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bancopichincha.tienda.dto.ProductRequest;
import com.bancopichincha.tienda.dto.ProductResponse;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.services.IProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private IProductService productService;

	@GetMapping("/product")
	public ResponseEntity<List<ProductEntity>> findAll() {
		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/product/name")
	public ResponseEntity<List<ProductResponse>> findAllName() {
		return new ResponseEntity<>(productService.findAllName(), HttpStatus.OK);
	}
	
	@PostMapping("/product")
	public ResponseEntity<HttpStatus> createNotification(@Validated @RequestBody ProductRequest productRequest) {
		productService.create(productRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<HttpStatus> updateNotification(@Validated @RequestBody ProductRequest productRequest) {
		productService.update(productRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
