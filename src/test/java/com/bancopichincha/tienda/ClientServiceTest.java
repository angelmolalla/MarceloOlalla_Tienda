package com.bancopichincha.tienda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.entity.ClientEntity;
import com.bancopichincha.tienda.services.IClientService;

@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private IClientService clientService;
	
	@Test
	void createClient() {
		ClientRequest clientRequest = new ClientRequest();
		clientRequest.setNumberIdentification("1725499634");
		clientRequest.setName("Marcelo Olalla");
		clientService.create(clientRequest);
		ClientEntity clientEntity =  clientService.findByNumberIdentification("1725499634");
		assertEquals(clientEntity.getNumberIdentification(), clientRequest.getNumberIdentification());
	}
	
	@Test
	void createClients() {
		List<ClientRequest> clientRequestList = new ArrayList<>();
		
		ClientRequest clientRequest1 = new ClientRequest();
		ClientRequest clientRequest2 = new ClientRequest();
		ClientRequest clientRequest3 = new ClientRequest();
		ClientRequest clientRequest4 = new ClientRequest();
		clientRequest1.setNumberIdentification("17071005406");
		clientRequest1.setName("Alexandra Olalla");
		
		clientRequest2.setNumberIdentification("1725665010");
		clientRequest2.setName("Marcia Larco");
		
		clientRequest3.setNumberIdentification("1707665020");
		clientRequest3.setName("Sebastian Jimenez");
		
		clientRequest4.setNumberIdentification("1737254676");
		clientRequest4.setName("Carlos Cartagena");
		
		for(ClientRequest clientRequest:clientRequestList ) {
			clientService.create(clientRequest);
		}
		
		
	}
}
