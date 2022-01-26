package com.bancopichincha.tienda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bancopichincha.tienda.entity.ClientEntity;

public interface IClientDao extends CrudRepository<ClientEntity, Long>{

	@Query(value = "SELECT u FROM ClientEntity u where u.state = 1")
	public Optional<List<ClientEntity>> findList();
	
	@Query(value = "SELECT u FROM ClientEntity u where u.state = 1 and u.numberIdentification=?1")
	public Optional<ClientEntity> findByNumberIdentification(String numberIdentification);
	
}
