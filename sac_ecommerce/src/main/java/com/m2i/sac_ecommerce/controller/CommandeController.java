package com.m2i.sac_ecommerce.controller;

import com.m2i.sac_ecommerce.entities.CategorieEntity;
import com.m2i.sac_ecommerce.entities.ClientEntity;
import com.m2i.sac_ecommerce.entities.CommandeEntity;
import com.m2i.sac_ecommerce.service.CategorieService;
import com.m2i.sac_ecommerce.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private CommandeService comservice;


    //param page : numéro de la page actuelle
    // size : nbre d'élements par page
    @GetMapping(value = "")
    public String list(Model model, HttpServletRequest request, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        String search = request.getParameter("search");


        Page<CommandeEntity> commandes = comservice.findAllByPage(page, size, search);

        model.addAttribute("commandes", commandes);
        model.addAttribute("error", request.getParameter("error"));
        model.addAttribute("success", request.getParameter("success"));
        model.addAttribute("search", search);

        model.addAttribute("pageCurrent", page);

        model.addAttribute("pages", new int[commandes.getTotalPages()]);

        return "commande/list_commande";
    }


    // http://localhost:8080/categorie/add
    @GetMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("categorie", new CategorieEntity());
        return "categorie/add_edit";
    }

    @PostMapping(value = "/add")
    public String addPost(HttpServletRequest request, Model model) {

        // Récupération des paramètres envoyés en POST
        String dateCommande = request.getParameter("dateCommande");
        dateCommande = dateCommande.replace("T", " ");
        String dateLivraison = request.getParameter("dateLivraison");
        int clientId = Integer.parseInt(request.getParameter("client"));
        /* Recuperation du id du client de cette commande */
        ClientEntity ce = new ClientEntity();
        ce.setIdClient(clientId);
        /* Affichage des dates ( liraison et commande ) */
        System.out.println("Date et heure de date de Commande  : " + dateCommande);
        System.out.println("Date et heure de date de liraison  : " + dateLivraison);

        // Préparation de l'entité à sauvegarder
        CommandeEntity com = new CommandeEntity((Timestamp.valueOf(dateCommande)), Date.valueOf(dateLivraison), ce);

        // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
        try {
            comservice.addCommande(com);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("commande", com);
            model.addAttribute("error", e.getMessage());
            return "commande/add_edit";
        }

        return "redirect:/commande?success=true";
    }


    public CommandeService getCommandeService() {
        return comservice;
    }

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/edit/{id_commande}")
    public String editGetPost(Model model, @PathVariable int id_commande, HttpServletRequest request) {
        System.out.println("Add Edit Commande" + request.getMethod());

        if (request.getMethod().equals("POST")) {
            // Récupération des paramètres envoyés en POST


            // Timestamp dateheure, Integer duree, String note, String type, PatientEntity patient
            // Préparation de l'entité à sauvegarder

            /*--------------------------------------------------*/
            String dateCommande = request.getParameter("dateCommande");
            dateCommande = dateCommande.replace("T", " ");
            String dateLivraison = request.getParameter("dateLivraison");
            // dateLivraison = dateLivraison.replace("T" , " ");
            int clientId = Integer.parseInt(request.getParameter("client"));

            ClientEntity ce = new ClientEntity();
            ce.setIdClient(clientId);

            System.out.println("Date et heure de date de Commande  : " + dateCommande);
            System.out.println("Date et heure de date de liraison  : " + dateLivraison);

            // Préparation de l'entité à sauvegarder
            CommandeEntity com = new CommandeEntity((Timestamp.valueOf(dateCommande)), Date.valueOf(dateLivraison), ce);


            // Enregistrement en utilisant la couche service qui gère déjà nos contraintes
            try {
                comservice.editCommande(id_commande, com);
            } catch (Exception e) {
                com.setIdCommande(-1); // hack
                System.out.println(e.getMessage());
                model.addAttribute("commande", com);
                model.addAttribute("error", e.getMessage());
                return "commande/add_edit";
            }
            return "redirect:/commande?success=true";
        } else {
            try {
                model.addAttribute("commande", comservice.findCommande(id_commande));
            } catch (NoSuchElementException e) {
                return "redirect:/commande?error=commande%20introuvalble";
            }

            return "commande/add_edit";
        }
    }


    @GetMapping(value = "/delete/{id_commande}")
    public String delete( @PathVariable int id_commande ){
        String message = "?success=true";
        try{
            comservice.delete(id_commande);
        }catch ( Exception e ){
            message = "?error=commande%20introuvalble";
        }
        return "redirect:/commande"+message;
    }

    public void setCommandeService(CommandeService comservice) {
        this.comservice = comservice;
    }






}
