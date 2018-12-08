package com.cas.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("test")
    public String main(String[] args) {

        return "olkk";
    }
}
