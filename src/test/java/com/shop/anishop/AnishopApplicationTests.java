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
		ItemEntity item = new ItemEntity();
		item.setItemName("피규어1");
		item.setItemDetail("첫 등록상품 피규어1");
		item.setPrice(10000);
		item.setStockNumber(10);
		item.setUpdateTime(LocalDateTime.now());
		item.setRegTime(LocalDateTime.now());

		ItemEntity savedItem = itemRepository.save(item);


		UserEntity user = new UserEntity();
		user.setName("테스트인원1");
		user.setPassword("12345567788");
		user.setEmail("perudeim@naver.com");
		user.setPhoneNumber("010-6546-4444");
		user.setUserGender(UserGender.MALE);

		UserEntity savedUser = userRepository.save(user);
	}

}
