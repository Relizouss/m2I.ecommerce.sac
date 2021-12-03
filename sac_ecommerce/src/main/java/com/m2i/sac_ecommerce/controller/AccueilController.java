package com.m2i.sac_ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accueil")
public class AccueilController{

    @GetMapping(value="")
    public String accueil(){
        return "accueil";
    }
}