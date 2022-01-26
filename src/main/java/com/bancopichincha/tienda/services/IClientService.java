package com.bancopichincha.tienda.services;

import java.util.List;
import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.entity.ClientEntity;

public interface IClientService {

	public List<ClientEntity> findAll();
	
	public ClientEntity findByNumberIdentification(String numberIdentification);
	
	public void create(ClientRequest clientRequest);
	
	public void update(ClientRequest clientRequest);
	
	public void delete(String numberIdentification);
	
}
