package com.bancopichincha.tienda.dto;

import lombok.Data;

@Data
public class OrderRequest {

	private String codProduct;
	
	private String codStore;
	
	private Integer amount;
}
