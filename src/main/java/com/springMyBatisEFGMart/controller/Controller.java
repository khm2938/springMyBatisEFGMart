package com.springMyBatisEFGMart.controller;

import com.springMyBatisEFGMart.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

	@Autowired
    private final ItemMapper itemMapper;

    @GetMapping("/items")
    public String itemList(Model model) {
        model.addAttribute("items", itemMapper.selectItemList());
        return "item/list";
    }
}
