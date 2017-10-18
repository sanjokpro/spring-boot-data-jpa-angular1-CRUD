package com.sanjok.springbootdataangular1crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET)
public class RootController {
    @GetMapping
    public String index() {
        return "To Login POST[/api/login]";
    }
}