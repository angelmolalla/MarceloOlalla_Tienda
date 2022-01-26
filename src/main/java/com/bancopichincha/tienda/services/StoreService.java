package com.bancopichincha.tienda.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bancopichincha.tienda.dao.IProductDao;
import com.bancopichincha.tienda.dao.IStoreDao;
import com.bancopichincha.tienda.dto.StoreRequest;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.entity.StoreEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.exception.NotFoundException;

@Service
public class StoreService implements IStoreService{

	@Autowired
	private IStoreDao storeDao;
	
	@Autowired
	private IProductDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<StoreEntity> findAll(){
		return storeDao.findList().orElse(new ArrayList<>()); 
	}

	@Override
	@Transactional
	public void prodAssignment(StoreRequest storeRequest) {
		try {
			StoreEntity storeEntity = storeDao.findByCod(storeRequest.getCodStore()).orElseThrow(() -> new NotFoundException(StoreRequest.class, "codStore",storeRequest.getCodStore()));
			List<ProductEntity> productList = productDao.findListByCodes(storeRequest.getCodProductList()).orElseThrow(() -> new NotFoundException(ProductEntity.class, "codProductList",storeRequest.getCodProductList()));
			storeEntity.setListProduct(productList);
			storeDao.save(storeEntity);
		}
		catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al asignar el producto en la tienda", e.getMessage());
		}

	}
	
}
