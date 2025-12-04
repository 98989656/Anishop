package com.shop.anishop.Item.service;


import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.entity.ItemImg;
import com.shop.anishop.Item.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemImgService {


    private final ItemImgRepository itemImgRepository;
    private final String uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";



    public void saveItemImage(ItemEntity savedItem, List<MultipartFile> files) throws IOException {

        if (files == null || files.isEmpty()) {
            return;
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(uploadDir, fileName);

            saveFile.getParentFile().mkdirs();

            file.transferTo(saveFile);

            ItemImg itemImg = new ItemImg();
            itemImg.setItem(savedItem);
            itemImg.setImagePath("/files/" + fileName);

            itemImgRepository.save(itemImg);
        }
    }

    public void updateItemImage(ItemEntity item,
                                List<MultipartFile> newItemImg,
                                List<Long> deleteImageIds) throws IOException {
        if (deleteImageIds != null && !deleteImageIds.isEmpty()) {
            for (Long imgId : deleteImageIds) {
                itemImgRepository.findById(imgId).ifPresent(img -> {
                    deleteFiles(img);
                    itemImgRepository.delete(img);
                });
            }
        }
        saveItemImage(item, newItemImg);
    }

    private void deleteFiles(ItemImg img) {
        if (img.getImagePath() == null)
            return;

        String filesName = img.getImagePath().replace("/files/", "");
        File file = new File(uploadDir, filesName);
        if (file.exists()) {
            file.delete();
        }
    }



}
