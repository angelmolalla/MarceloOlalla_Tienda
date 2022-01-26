package com.bancopichincha.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bancopichincha.tienda.dto.OrderTransactionRequest;
import com.bancopichincha.tienda.services.IOrderTransactionService;

@RestController
@RequestMapping("/api")
public class OrderTransactionRestController {

	@Autowired
	private IOrderTransactionService orderTransactionService;
	
	@PostMapping("/orderTransaction")
	public ResponseEntity<HttpStatus> orderTransactionService(@Validated @RequestBody OrderTransactionRequest orderTransactionRequest) {
		orderTransactionService.makeOrder(orderTransactionRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
