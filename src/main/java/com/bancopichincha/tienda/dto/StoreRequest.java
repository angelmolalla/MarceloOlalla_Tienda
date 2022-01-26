package com.bancopichincha.tienda.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreRequest {

	@NotBlank(message = "El código de la tienda esta vacio")
	private String codStore;
	
	private List<String> codProductList;
		
}
