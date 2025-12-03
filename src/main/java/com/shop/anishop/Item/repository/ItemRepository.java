package com.shop.anishop.Item.repository;

import com.shop.anishop.Item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByItemName(String itemName);



}
