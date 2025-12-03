package com.shop.anishop.Item.controller;

import com.shop.anishop.Item.dto.ItemDto;
import com.shop.anishop.Item.entity.ItemEntity;
import com.shop.anishop.Item.repository.ItemRepository;
import com.shop.anishop.Item.serivce.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public String list(Model model) {
        List<ItemEntity> itemList = this.itemService.getList();
        model.addAttribute("itemList", itemList);
        return "item/list";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        ItemEntity itemDetail = this.itemService.getItem(id);
        model.addAttribute("item", itemDetail);
        return "item/detail";
    }

    @GetMapping("/create")
    public String create(ItemDto itemDto) {
        return "item/create";
    }

    @PostMapping("/create")
    public String create(@Valid ItemDto itemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "item/create";
        }
        this.itemService.create(itemDto.getItemName(), itemDto.getPrice(), itemDto.getStockNumber(), itemDto.getItemDetail());
        return "redirect:/item/list";
    }

    @GetMapping("/modify/{id}")
    public String modify(ItemDto itemDto, @PathVariable("id") Long id) {
        ItemEntity item = this.itemService.getItem(id);

        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemDetail(item.getItemDetail());
        itemDto.setPrice(item.getPrice());
        itemDto.setStockNumber(item.getStockNumber());

        return "item/modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(@Valid ItemDto itemDto, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "item/modify";
        }
        ItemEntity item = this.itemService.getItem(id);
        this.itemService.modify(item, itemDto.getItemName(), itemDto.getPrice(), itemDto.getStockNumber(), itemDto.getItemDetail());
        return String.format("redirect:/item/detail/%s", id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        ItemEntity item = this.itemService.getItem(id);

        this.itemService.delete(item);
        return "redirect:/item/list";
    }


}