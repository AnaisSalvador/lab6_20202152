package com.example.lab6_salvador_anais.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @GetMapping("/index")
    public String index(){
        return "gticsf";
    }
}
