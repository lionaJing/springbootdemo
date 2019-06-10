package com.lemon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lemon
 */
@RestController
public class MyController {
    @GetMapping(value = "/say")
    public String say() {
        System.out.println("/say----controller-----");
        return "hello world!...";
    }
}
