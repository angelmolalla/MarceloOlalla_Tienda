package com.bancopichincha.tienda.services;

import java.util.List;

import com.bancopichincha.tienda.dto.StoreRequest;
import com.bancopichincha.tienda.entity.StoreEntity;

public interface IStoreService {

	public List<StoreEntity> findAll();
	
	public void prodAssignment(StoreRequest storeRequest);
}
