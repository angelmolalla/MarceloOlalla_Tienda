package com.bancopichincha.tienda.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bancopichincha.tienda.dao.IClientDao;
import com.bancopichincha.tienda.dao.IOrderTransactionDao;
import com.bancopichincha.tienda.dao.IProductDao;
import com.bancopichincha.tienda.dao.IStoreDao;
import com.bancopichincha.tienda.dto.OrderRequest;
import com.bancopichincha.tienda.dto.OrderTransactionRequest;
import com.bancopichincha.tienda.entity.ClientEntity;
import com.bancopichincha.tienda.entity.OrderTransactionEntity;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.entity.StoreEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.exception.IntegrityViolationException;
import com.bancopichincha.tienda.exception.NotFoundException;

@Service
public class OrderTransactionService implements IOrderTransactionService{

	@Autowired
	private IClientDao clientDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Autowired
	private IStoreDao storeDao;
	
	@Autowired
	private IOrderTransactionDao orderTransactionDao;
	
	
	@Override
	@Transactional
	public void makeOrder(OrderTransactionRequest orderTransactionRequest) {

		try {
			List<OrderTransactionEntity> orderTransactionList = new ArrayList<>();
			List<ProductEntity> productList = new ArrayList<>();
			Boolean checkResultAmount = false;
			ProductEntity productCheckResult = new ProductEntity();
			ClientEntity clientEntity = clientDao.findByNumberIdentification(orderTransactionRequest.getNumberIdentificationClient()).orElseThrow(() -> new NotFoundException(ClientEntity.class, "numberIdentificatio",orderTransactionRequest.getNumberIdentificationClient()));
			
			for (OrderRequest order: orderTransactionRequest.getOrderList()) {
				OrderTransactionEntity orderTransactionEntity = new OrderTransactionEntity();
				
				StoreEntity store = storeDao.findByCod(order.getCodStore()).orElseThrow(() -> new NotFoundException(StoreEntity.class, "codStore", order.getCodStore()));
				ProductEntity product = store.getListProduct().stream().filter(temp-> temp.getCod().equals(order.getCodProduct())).findAny().orElseThrow(() -> new NotFoundException(ProductEntity.class, "codProd",order.getCodProduct()));
				Integer result = product.getStock()-order.getAmount();
				
				orderTransactionEntity.setClientEntity(clientEntity);
				orderTransactionEntity.setProductEntity(product);
				orderTransactionEntity.setStoreEntity(store);
				orderTransactionEntity.setCreationDate(LocalDateTime.now());
				orderTransactionEntity.setAmount(order.getAmount());
				if(result<-10) {
					throw new IntegrityViolationException(ProductEntity.class, "Stock", "Unidades no disponibles (> 10)");
				}
				else if(result<-5 && result>=-10 && !checkResultAmount) {
					product.setStock(product.getStock()+10);
					productCheckResult = product;
					checkResultAmount = true;
				}
				else if(result<=0 && result >=-5 && !checkResultAmount) {
					product.setStock(product.getStock()+5);
					productList.add(product);
				} else if(!checkResultAmount) {
					product.setStock(result);
					orderTransactionList.add(orderTransactionEntity);
					productList.add(product);
				}
			}
			
			if(checkResultAmount) {
				productDao.save(productCheckResult);
				
			} else {
				productDao.saveAll(productList);
				orderTransactionDao.saveAll(orderTransactionList);
			}
		}
		catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al realizar la transacción", e.getMessage());
		}
		
		
		
	}

}
