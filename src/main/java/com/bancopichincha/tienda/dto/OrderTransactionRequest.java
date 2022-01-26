package com.bancopichincha.tienda.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderTransactionRequest {

	private String numberIdentificationClient;
	
	private List<OrderRequest> orderList;
}
