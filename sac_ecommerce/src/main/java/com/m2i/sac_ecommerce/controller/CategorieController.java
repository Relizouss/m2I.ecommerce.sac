package com.m2i.sac_ecommerce.controller;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.service.CategorieService;
import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private CategorieService catservice;

    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list( Model model, HttpServletRequest request , @RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name="size", defaultValue="5")int size ){
        String search = request.getParameter("search");


        Page<CategorieEntity> categories = catservice.findAllByPage(page , size, search);

        model.addAttribute("categories" , categories );
        model.addAttribute( "error" , request.getParameter("error") );
        model.addAttribute( "success" , request.getParameter("success") );
        model.addAttribute( "search" , search );

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[categories.getTotalPages()]);

        return "categorie/list_categorie";
    }

    // http://localhost:8080/categorie/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("categorie" , new CategorieEntity() );
        return "categorie/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){

        // Récupération des paramètres envoyés en POST
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

        // Préparation de l'entité à sauvegarder
       CategorieEntity cat = new CategorieEntity(nom, description );

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            catservice.addCategorie( cat );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("categorie" , cat );
            model.addAttribute("error" , e.getMessage() );
            return "categorie/add_edit";
        }

        return "redirect:/categorie?success=true";
    }

    public CategorieService getCatservice() {
        return catservice;
    }

    @RequestMapping( method = { RequestMethod.GET , RequestMethod.POST} , value = "/edit/{codeCategorie}" )
    public String editGetPost(Model model , @PathVariable int codeCategorie, HttpServletRequest request ){
        System.out.println( "Add Edit Categorie" + request.getMethod() );

        if( request.getMethod().equals("POST") ){
            // Récupération des paramètres envoyés en POST

            String nom = request.getParameter("nom");
            String description = request.getParameter("description");

            // Préparation de l'entité à sauvegarder
            CategorieEntity cat = new CategorieEntity(nom, description );


            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                catservice.editCategorie( codeCategorie, cat );
            }catch( Exception e ){
                cat.setCodeCategorie(  -1 ); // hack
                System.out.println( e.getMessage() );
                model.addAttribute("categorie" , cat );
                model.addAttribute("error" , e.getMessage() );
                return "categorie/add_edit";
            }
            return "redirect:/categorie?success=true";
        }else{
            try{
                model.addAttribute("categorie" , catservice.findCategorie(codeCategorie));
            }catch ( NoSuchElementException e ){
                return "redirect:/categorie?error=Categorie%20introuvalble";
            }

            return "categorie/add_edit";
        }
    }

    @GetMapping(value = "/delete/{codeCategorie}")
    public String delete( @PathVariable int codeCategorie ){
        String message = "?success=true";
        try{
            catservice.delete(codeCategorie);
        }catch ( Exception e ){
            message = "?error=Categorie%20introuvable";
        }
        return "redirect:/categorie"+message;
    }
//
//    //public void setCatservice(CategorieService catservice) {
//        this.catservice = catservice;
//    }
}

