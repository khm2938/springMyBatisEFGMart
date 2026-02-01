package com.springMyBatisEFGMart.controller;

import com.springMyBatisEFGMart.mapper.EFGMartMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class MyBatisTestController {

    private final EFGMartMapper efgMartMapper;

    public MyBatisTestController(EFGMartMapper efgMartMapper) {
        this.efgMartMapper = efgMartMapper;
    }

    @GetMapping("/mybatis-test")
    public String mybatisTest(Model model) {
        int count = efgMartMapper.countItems();

        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("itemCount", count);

        return "mybatis-test"; // /WEB-INF/views/mybatis-test.jsp
    }
}
