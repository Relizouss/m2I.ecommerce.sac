package com.m2i.sac_ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController{

    @GetMapping(value="")
    public String accueil(){
        return "accueil";
    }
}