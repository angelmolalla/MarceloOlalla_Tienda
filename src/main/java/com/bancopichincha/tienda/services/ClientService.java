package com.bancopichincha.tienda.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bancopichincha.tienda.dao.IClientDao;
import com.bancopichincha.tienda.dto.ClientRequest;
import com.bancopichincha.tienda.entity.ClientEntity;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.exception.IntegrityViolationException;
import com.bancopichincha.tienda.exception.NotFoundException;

@Service
public class ClientService implements IClientService{

	@Autowired
	private IClientDao clientDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientEntity> findAll() {
		return clientDao.findList().orElse(new ArrayList<>());
	}

	@Override
	@Transactional(readOnly = true)
	public ClientEntity findByNumberIdentification(String numberIdentification) {
		return clientDao.findByNumberIdentification(numberIdentification).orElseThrow(() -> new NotFoundException(ClientEntity.class, "numberIdentification", numberIdentification));
	}

	@Override
	@Transactional
	public void create(ClientRequest clientRequest) {
		try {
			ClientEntity clientEntity = new ClientEntity();
			
			if(clientDao.findByNumberIdentification(clientRequest.getNumberIdentification()).isPresent()) {
				throw new IntegrityViolationException(ClientEntity.class, "numberIdentification", clientRequest.getNumberIdentification());
			}
			clientEntity.setNumberIdentification(clientRequest.getNumberIdentification());
			clientEntity.setName(clientRequest.getName());
			clientEntity.setState(true);
			clientDao.save(clientEntity);
		} catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al guardar el cliente", e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void update(ClientRequest clientRequest) {
		try {
			ClientEntity clientEntity = clientDao.findByNumberIdentification(clientRequest.getNumberIdentification()).orElseThrow(() -> new NotFoundException(ClientEntity.class, "numberIdentificatio",clientRequest.getNumberIdentification()));
			clientEntity.setName(clientRequest.getName());
			clientDao.save(clientEntity);
		} catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al modificar el cliente", e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void delete(String numberIdentification) {
		try {
			ClientEntity clientEntity = clientDao.findByNumberIdentification(numberIdentification).orElseThrow(() -> new NotFoundException(ClientEntity.class, "numberIdentificatio",numberIdentification));
			clientEntity.setState(false);
			clientDao.save(clientEntity);
		} catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al eliminar el cliente", e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public void updatePhoto(MultipartFile file, String numberIdentification) {
		ClientEntity clientEntity = clientDao.findByNumberIdentification(numberIdentification).orElseThrow(() -> new NotFoundException(ClientEntity.class, "numberIdentificatio",numberIdentification));
		if(!file.isEmpty()) {
			String nameFile = UUID.randomUUID()+"_"+file.getOriginalFilename().replace(" ", "");
			Path pathFile=Paths.get("uploads").resolve(nameFile).toAbsolutePath();
			
			try {
				Files.copy(file.getInputStream(), pathFile);
			} catch (IOException e) {
				throw new DataException(ProductEntity.class, "Error al subir la imagen del cliente", e.getMessage());
			}
			clientEntity.setPhoto(nameFile);
			clientDao.save(clientEntity);
		}
		
	}

}
