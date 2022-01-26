package com.bancopichincha.tienda.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequest {

	@NotBlank(message = "El c�digo del producto esta vacio")
	private String cod;
	
	@NotBlank(message = "El nombre del producto esta vacio")
	private String name;
	
	@NotNull(message = "El precio del producto esta vacio")
	private Double price;

	@NotNull(message = "El stock del proudcto esta vacio")
	private Integer stock;

}
