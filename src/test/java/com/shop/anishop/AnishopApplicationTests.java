package com.shop.anishop;

import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.repository.ItemRepository;
import com.shop.anishop.user.entity.UserEntity;
import com.shop.anishop.user.num.UserGender;
import com.shop.anishop.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AnishopApplicationTests {


	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	@Test
	void createdTest1() {

		for (int i = 0; i <= 100; i++){
			ItemEntity item = new ItemEntity();
			item.setItemName("피규어 " + i);
			item.setItemDetail("첫 등록상품 피규어" + i);
			item.setPrice(10000);
			item.setStockNumber(10);
			item.setUpdateTime(LocalDateTime.now());
			item.setRegTime(LocalDateTime.now());

			ItemEntity savedItem = itemRepository.save(item);
		}
	}

}
