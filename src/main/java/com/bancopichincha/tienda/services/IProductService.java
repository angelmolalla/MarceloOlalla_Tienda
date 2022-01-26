package com.bancopichincha.tienda.services;

import java.util.List;
import com.bancopichincha.tienda.dto.ProductRequest;
import com.bancopichincha.tienda.dto.ProductResponse;
import com.bancopichincha.tienda.entity.ProductEntity;

public interface IProductService {
	
	public List<ProductEntity> findAll();
	
	public List<ProductResponse> findAllName();
	
	public void create(ProductRequest productRequest);
	
	public void update(ProductRequest productRequest);
}

