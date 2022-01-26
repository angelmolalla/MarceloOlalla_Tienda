package com.bancopichincha.tienda;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bancopichincha.tienda.dto.ProductRequest;
import com.bancopichincha.tienda.services.IProductService;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private IProductService productService;
	
	@Test
	void createProduct() {
		
		ProductRequest productRequest = new ProductRequest();

		productRequest.setCod("pod0");
		productRequest.setName("azucar");
		productRequest.setPrice((double) 1);
		productRequest.setStock(20);
		productService.create(productRequest);
		
	}
	
	@Test
	void createProducts() {
		List<ProductRequest> productRequestList= new ArrayList<>();
		ProductRequest productRequest1 = new ProductRequest();
		ProductRequest productRequest2 = new ProductRequest();
		productRequest1.setCod("pod1");
		productRequest1.setName("cafe");
		productRequest1.setPrice((double) 1);
		productRequest1.setStock(10);
		
		productRequest2.setCod("pod2");
		productRequest2.setName("te");
		productRequest2.setPrice((double) 2);
		productRequest2.setStock(20);
		
		productRequestList.add(productRequest1);
		productRequestList.add(productRequest2);
		
		for(ProductRequest productRequest:productRequestList) {
			productService.create(productRequest);
		}
	}
}
