package com.bancopichincha.tienda.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bancopichincha.tienda.entity.OrderTransactionEntity;

public interface IOrderTransactionDao  extends CrudRepository<OrderTransactionEntity, Long>{

	@Query(value = "SELECT u FROM OrderTransactionEntity u")
	public Optional<List<OrderTransactionEntity>> findList();
	
	@Query(value = "SELECT u FROM OrderTransactionEntity u WHERE u.storeEntity.cod=?1")
	public Optional<List<OrderTransactionEntity>> findListByStore(String codStore);
	
	@Query(value = "SELECT count(u) FROM OrderTransactionEntity u WHERE u.creationDate BETWEEN :startDate AND :endDate AND u.storeEntity.cod = :codStore")
	public Optional<Long> findCountByStoreAndDate(@Param("codStore")String codStore,@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
	
	@Query(value = "SELECT u FROM OrderTransactionEntity u WHERE u.storeEntity.cod=?1 AND u.productEntity.cod=?2")
	public Optional<List<OrderTransactionEntity>> findListByStoreAndProduct(String codStore,String codProduc);
	
	@Query(value = "SELECT u FROM OrderTransactionEntity u where u.creationDate BETWEEN :startDate AND :endDate AND u.clientEntity.numberIdentification = :numberIdentification")
	public Optional<List<OrderTransactionEntity>> findListByIdentificationAndDate(@Param("numberIdentification")String numberIdentification,@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
	
	
}
