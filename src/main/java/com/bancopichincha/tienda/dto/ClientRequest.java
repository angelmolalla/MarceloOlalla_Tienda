package com.bancopichincha.tienda.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientRequest {

	@NotBlank(message = "La identificacion del cliente esta vacio")
	private String numberIdentification;
	
	@NotBlank(message = "El nombre del cliente esta vacio")
	private String name;
	
	private String photo;

	
}
