package com.bancopichincha.tienda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bancopichincha.tienda.dao.IProductDao;
import com.bancopichincha.tienda.dto.ProductRequest;
import com.bancopichincha.tienda.dto.ProductResponse;
import com.bancopichincha.tienda.entity.ProductEntity;
import com.bancopichincha.tienda.exception.DataException;
import com.bancopichincha.tienda.exception.IntegrityViolationException;
import com.bancopichincha.tienda.exception.NotFoundException;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductDao productDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductEntity> findAll(){
		return productDao.findList().orElse(new ArrayList<>()); 
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductResponse> findAllName() {
	return productDao.findList().orElse(new ArrayList<>()).stream().map(temp->{
			ProductResponse productResponse = new ProductResponse();
			productResponse.setCod(temp.getCod());
			productResponse.setName(temp.getName());
			return productResponse;
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void create(ProductRequest productRequest) {
		try {
		ProductEntity productEntity = new ProductEntity();
			
		if(productDao.findByCod(productRequest.getCod()).isPresent()) {
			throw new IntegrityViolationException(ProductEntity.class, "cod", productRequest.getCod());
		}
		
		if(productRequest.getStock() <= 0) {
			throw new IntegrityViolationException(ProductEntity.class, "stock", productRequest.getStock());
		}
		
		productEntity.setCod(productRequest.getCod());
		productEntity.setName(productRequest.getName());
		productEntity.setPrice(productRequest.getPrice());
		productEntity.setStock(productRequest.getStock());
		productDao.save(productEntity);
		} catch (DataAccessException e) {
			throw new DataException(ProductEntity.class, "Error al guardar el producto", e.getMessage());
		}
	}

	@Override
	@Transactional
	public void update(ProductRequest productRequest) {
		try {
			ProductEntity productEntity = productDao.findByCod(productRequest.getCod()).orElseThrow(() -> new NotFoundException(ProductEntity.class, "cod", productRequest.getCod()));
			
			if(productRequest.getStock() <= 0) {
				throw new IntegrityViolationException(ProductEntity.class, "stock", productRequest.getStock());
			}
			
			productEntity.setName(productRequest.getName());
			productEntity.setPrice(productRequest.getPrice());
			productEntity.setStock(productRequest.getStock());
			productDao.save(productEntity);
			} catch (DataAccessException e) {
				throw new DataException(ProductEntity.class, "Error al guardar el producto", e.getMessage());
			}
	}

}
