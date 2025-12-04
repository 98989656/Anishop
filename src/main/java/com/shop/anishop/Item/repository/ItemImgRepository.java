package com.shop.anishop.Item.repository;

import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findAllByItem(ItemEntity item);

}
