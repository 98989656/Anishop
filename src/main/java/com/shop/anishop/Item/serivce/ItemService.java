package com.shop.anishop.Item.serivce;

import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;

    public List<ItemEntity> getList() {
        return this.itemRepository.findAll();
    }

    public ItemEntity getItem(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("item not found"));
    }

    public void create(String itemName, int price, int stockNumber, String itemDetail) {
        ItemEntity i = new ItemEntity();
        i.setItemName(itemName);
        i.setPrice(price);
        i.setStockNumber(stockNumber);
        i.setItemDetail(itemDetail);
        i.setRegTime(LocalDateTime.now());
        i.setUpdateTime(LocalDateTime.now());
        this.itemRepository.save(i);
    }

    public void modify(ItemEntity i, String itemName, int price, int stockNumber, String itemDetail) {
        i.setItemName(itemName);
        i.setPrice(price);
        i.setStockNumber(stockNumber);
        i.setItemDetail(itemDetail);
        i.setUpdateTime(LocalDateTime.now());
        this.itemRepository.save(i);
    }


}