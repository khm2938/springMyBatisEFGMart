package com.springMyBatisEFGMart.controller;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test")
    public String test(Model model) {
        // Oracle 연결 테스트: DUAL에서 1 조회
        Integer one = jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);

        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("dbResult", one);

        return "test"; // /WEB-INF/views/test.jsp
    }
}
