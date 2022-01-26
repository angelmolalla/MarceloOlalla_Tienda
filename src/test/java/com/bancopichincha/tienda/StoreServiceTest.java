package com.bancopichincha.tienda;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bancopichincha.tienda.entity.StoreEntity;
import com.bancopichincha.tienda.services.IStoreService;

@SpringBootTest
public class StoreServiceTest {

	@Autowired
	private IStoreService storeService;
	
	@Test
	void findList(){
		List<StoreEntity> storeEntityList= storeService.findAll();
		assertNotNull(storeEntityList);
	}
}
