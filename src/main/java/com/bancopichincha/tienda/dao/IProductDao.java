package com.bancopichincha.tienda.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.bancopichincha.tienda.entity.ProductEntity;


public interface IProductDao extends CrudRepository<ProductEntity, Long>{
	
	@Query(value = "SELECT u FROM ProductEntity u")
	public Optional<List<ProductEntity>> findList();
	
	@Query(value = "SELECT u FROM ProductEntity u WHERE u.cod=?1")
	public Optional<ProductEntity> findByCod(String cod);

	@Query("SELECT e FROM ProductEntity e WHERE e.cod IN (:codes)")
	Optional<List<ProductEntity>> findListByCodes(@Param("codes")List<String> codList);
	
}
