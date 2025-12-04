package com.shop.anishop.Item.service;

import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;


    public List<ItemEntity> getList() {
        return this.itemRepository.findAll();
    }

    public ItemEntity getItem(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("item not found"));
    }

    public void create(String itemName, int price, int stockNumber, String itemDetail,
                      List<MultipartFile> files ) throws IOException {

        ItemEntity i = new ItemEntity();
        i.setItemName(itemName);
        i.setPrice(price);
        i.setStockNumber(stockNumber);
        i.setItemDetail(itemDetail);
        i.setRegTime(LocalDateTime.now());
        i.setUpdateTime(LocalDateTime.now());

        ItemEntity savedItem = this.itemRepository.save(i);


        itemImgService.saveItemImage(savedItem, files);

    }

    public void modify(ItemEntity i, String itemName, int price, int stockNumber, String itemDetail, MultipartFile file) {
        i.setItemName(itemName);
        i.setPrice(price);
        i.setStockNumber(stockNumber);
        i.setItemDetail(itemDetail);
        i.setUpdateTime(LocalDateTime.now());
        this.itemRepository.save(i);
    }

    public void delete(ItemEntity item) {
        this.itemRepository.delete(item);
    }


}