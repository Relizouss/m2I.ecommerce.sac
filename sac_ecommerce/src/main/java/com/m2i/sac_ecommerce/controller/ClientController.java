package com.m2i.sac_ecommerce.controller;


import com.m2i.sac_ecommerce.entities.ClientEntity;
import com.m2i.sac_ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService cliservice;

    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request , @RequestParam(name = "page", defaultValue = "0") int page , @RequestParam(name="size", defaultValue="5")int size ){
        String search = request.getParameter("search");


        Page<ClientEntity> clients = cliservice.findAllByPage(page , size, search);

        model.addAttribute("clients" , clients );
        model.addAttribute( "error" , request.getParameter("error") );
        model.addAttribute( "success" , request.getParameter("success") );
        model.addAttribute( "search" , search );

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[clients.getTotalPages()]);

        return "client/list_client";
    }

    // http://localhost:8080/client/add
    @GetMapping(value = "/add")
    public String add( Model model ){
        model.addAttribute("client" , new ClientEntity() );
        return "client/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost( HttpServletRequest request , Model model ){

        // Récupération des paramètres envoyés en POST
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissance = request.getParameter("dateNaissance");
        String adresse = request.getParameter("adresse");
        String ville = request.getParameter("ville");
        String codePostal= request.getParameter("codePostal");
        String pays = request.getParameter("pays");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String carteNumero= request.getParameter("carteNumero");

        // Préparation de l'entité à sauvegarder
        ClientEntity cli = new ClientEntity(nom, prenom, Date.valueOf(dateNaissance), adresse, ville, codePostal, pays, telephone, email, password, carteNumero);
        System.out.println(cli);
        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try{
            cliservice.addClient( cli );
        }catch( Exception e ){
            System.out.println( e.getMessage() );
            model.addAttribute("client" , cli );
            model.addAttribute("error" , e.getMessage() );
            return "client/add_edit";
        }

        return "redirect:/client?success=true";
    }

    public ClientService getcliservice() {
        return cliservice;
    }

    @RequestMapping( method = { RequestMethod.GET , RequestMethod.POST} , value = "/edit/{idClient}" )
    public String editGetPost(Model model , @PathVariable int idClient, HttpServletRequest request ){
        System.out.println( "Add Edit Client" + request.getMethod() );

        if( request.getMethod().equals("POST") ){
            // Récupération des paramètres envoyés en POST

            // Récupération des paramètres envoyés en POST
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dateNaissance = request.getParameter("dateNaissance");
            String adresse = request.getParameter("adresse");
            String ville = request.getParameter("ville");
            String codePostal= request.getParameter("codePostal");
            String pays = request.getParameter("pays");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String carteNumero= request.getParameter("carteNumero");

            // Préparation de l'entité à sauvegarder
            ClientEntity cli = new ClientEntity(nom, prenom, Date.valueOf(dateNaissance), adresse, ville, codePostal, pays, telephone, email, password, carteNumero);

            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try{
                cliservice.editClient( idClient, cli );
            }catch( Exception e ){
                cli.setIdClient(  -1 ); // hack
                System.out.println( e.getMessage() );
                model.addAttribute("client" , cli );
                model.addAttribute("error" , e.getMessage() );
                return "client/add_edit";
            }
            return "redirect:/client?success=true";
        }else{
            try{
                model.addAttribute("client" , cliservice.findClient(idClient));
            }catch ( NoSuchElementException e ){
                return "redirect:/client?error=Client%20introuvalble";
            }

            return "client/add_edit";
        }
    }

    @GetMapping(value = "/delete/{idClient}")
    public String delete( @PathVariable int idClient ){
        String message = "?success=true";
        try{
            cliservice.delete(idClient);
        }catch ( Exception e ){
            message = "?error=Client%20introuvalble";
        }
        return "redirect:/client"+message;
    }

    public void setCliservice(ClientService cliservice) {
        this.cliservice = cliservice;
    }
}


