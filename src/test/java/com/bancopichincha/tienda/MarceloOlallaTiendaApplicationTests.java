package com.bancopichincha.tienda;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.services.IClientService;

@SpringBootTest
class MarceloOlallaTiendaApplicationTests {

	@Autowired
	private IClientService clientService;
	
	@Test
	void createClient() {
		ClientRequest clientRequest = new ClientRequest();
		clientRequest.setNumberIdentification("1725499634");
		clientRequest.setName("Marcelo Olalla");
		clientService.create(clientRequest);
	}

}
