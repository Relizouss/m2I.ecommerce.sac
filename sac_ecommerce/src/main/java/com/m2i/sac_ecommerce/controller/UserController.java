package com.m2i.sac_ecommerce.controller;


import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.ProduitEntity;
import com.m2i.sac_ecommerce.entities.UserEntity;
import com.m2i.sac_ecommerce.repositories.UserRepository;
import com.m2i.sac_ecommerce.service.CategorieService;
import com.m2i.sac_ecommerce.service.ProduitService;
import com.m2i.sac_ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request , @RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name="size", defaultValue="5")int size ){
        String search = request.getParameter("search");


        Page<UserEntity> users = userService.findAllByPage(page , size, search);

        model.addAttribute("users" , users );
        model.addAttribute( "error" , request.getParameter("error") );
        model.addAttribute( "success" , request.getParameter("success") );
        model.addAttribute( "search" , search );

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[users.getTotalPages()]);

        return "user/list_user";
    }


    // http://localhost:8080/user/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("user" , new UserEntity() );
        return "user/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){



        // Récupération des paramètres envoyés en POST
        String identifiant = request.getParameter("identifiant");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String password = request.getParameter("password");


        // Préparation de l'entité à sauvegarder
        UserEntity user = new UserEntity(identifiant, nom, email, role, password);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            userService.addUser( user );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("user" , user );
            model.addAttribute("error" , e.getMessage() );
            return "user/add_edit";
        }

        return "redirect:/user?success=true";
    }



    @RequestMapping( method = { RequestMethod.GET , RequestMethod.POST} , value = "/edit/{id}" )
    public String editGetPost(Model model , @PathVariable int idUser, HttpServletRequest request ){
        System.out.println( "Add Edit User" + request.getMethod() );

        if( request.getMethod().equals("POST") ){
            // Récupération des paramètres envoyés en POST

            // Récupération des paramètres envoyés en POST
            String identifiant = request.getParameter("identifiant");
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            String password = request.getParameter("password");

            // Préparation de l'entité à sauvegarder
            UserEntity user = new UserEntity(identifiant, nom, email, role, password );


            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                userService.editUser( idUser, user );
            }catch( Exception e ){
                user.setIdUser(  -1 ); // hack
                System.out.println( e.getMessage() );
                model.addAttribute("user" , user );
                model.addAttribute("error" , e.getMessage() );
                return "user/add_edit";
            }
            return "redirect:/user?success=true";
        }else{
            try{
                model.addAttribute("user" , userService.findUser(idUser));
            }catch ( NoSuchElementException e ){
                return "redirect:/user?error=user%20introuvalble";
            }

            return "user/add_edit";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete( @PathVariable int idUser ){
        String message = "?success=true";
        try{
            userService.delete(idUser);
        }catch ( Exception e ){
            message = "?error=user%20introuvalble";
        }
        return "redirect:/user"+message;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }



}
