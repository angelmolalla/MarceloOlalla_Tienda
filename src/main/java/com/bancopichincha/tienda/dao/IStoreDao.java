package com.bancopichincha.tienda.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bancopichincha.tienda.entity.StoreEntity;

public interface IStoreDao extends CrudRepository<StoreEntity, Long>{

	@Query(value = "SELECT u FROM StoreEntity u")
	public Optional<List<StoreEntity>> findList();
	
	@Query(value = "SELECT u FROM StoreEntity u WHERE u.cod=?1")
	public Optional<StoreEntity> findByCod(String cod);
	
}
